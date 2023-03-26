package com.matiastesio.cabify.ui.details

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.bumptech.glide.Glide
import com.matiastesio.cabify.R
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.databinding.DetailFragmentBinding
import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.setTextWithContentDescription
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : DialogFragment() {

    private val navArgs by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel: DetailsViewModel by viewModels()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        loadData()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun loadData() {
        detailsViewModel.getDetail(navArgs.code)
    }

    private fun setObservers() {
        detailsViewModel.detail.observe(viewLifecycleOwner) {
            showDetail(it)
        }

        detailsViewModel.emptyState.observe(viewLifecycleOwner) {
            if (it) {
                dismiss()
            }
        }

        detailsViewModel.addToCartState.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(this.activity, "Successfully added to cart!", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            } else {
                Toast.makeText(this.activity, "Something went wrong...", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }

    private fun showDetail(itemModel: CatalogItemModel?) {
        binding.addToCartButton.setOnClickListener {
            click(itemModel)
        }

        binding.goToCartButton.setOnClickListener {
            goToCartClick()
        }

        itemModel?.let {
            setTitle(it.name)
            setPrices(it.discount, it.price)
            setSubtitle(it.discount)
            setDiscountedPrice(it.price, it.discount)
            setImage(it.icon)

        }
    }

    private fun goToCartClick() {
        val action = DetailsFragmentDirections.actionDetailFragmentToCartFragment()

        val navOptions = navOptions {
            popUpTo(R.id.catalogFragment) {
                inclusive = true
            }
        }
        findNavController().navigate(action, navOptions)
    }

    private fun click(itemModel: CatalogItemModel?) {
        itemModel?.let {
            detailsViewModel.addToCart(it)
        } ?: run {
            dismiss()
        }
    }

    private fun setTitle(name: String) {
        binding.detailTitle.setTextWithContentDescription(name)
    }

    private fun setImage(icon: String) {
        binding.detailImage.apply {
            Glide.with(context)
                .asBitmap()
                .load(icon)
                .into(this)

            clipToOutline = true
        }
    }

    private fun setDiscountedPrice(price: Double?, discount: DiscountModel?) {
        discount?.let {
            binding.detailDiscountedPrice.text = String.format("$ %s", price.toString())
            binding.detailDiscountedPrice.paintFlags =
                binding.detailDiscountedPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } ?: run {
            binding.detailDiscountedPrice.visibility = GONE
        }
    }

    private fun setSubtitle(discount: DiscountModel?) {
        discount?.let {
            binding.detailSubTitle.text = discount.title
            binding.detailSubTitle.visibility = VISIBLE
        } ?: run {
            binding.detailSubTitle.visibility = GONE
        }
    }

    private fun setPrices(discount: DiscountModel?, price: Double) {
        discount?.let {
            if (it.discountType == FLAT_PRICE_DISCOUNT_TYPE) {
                setPrice(it.discount, VISIBLE)
            } else {
                setPrice(price, GONE)
            }
        } ?: run {
            setPrice(price, GONE)
        }
    }

    private fun setPrice(price: Double?, visibility: Int) {
        binding.detailPrice.setTextWithContentDescription(String.format("$ %s", price))
        binding.detailDiscountedPrice.visibility = visibility
    }
}