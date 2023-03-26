package com.matiastesio.cabify.ui.catalog.list

import android.graphics.Paint
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.matiastesio.cabify.R
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.databinding.ProductViewHolderBinding
import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.setTextWithContentDescription

class CatalogItemViewHolder(
    private val binding: ProductViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CatalogItemModel, onClickHandler: OnClickHandler) {
        showTitle(item.name)
        showImage(item.icon)
        showDiscountImage(item.discount)
        showDiscountedPrice(item.price, item.discount)
        showSecondaryTitle(item.discount)
        showPrice(item.price, item.discount)
        binding.root.setOnClickListener {
            item.code.let { it1 -> onClickHandler.click(it1) }
        }
    }

    private fun showSecondaryTitle(discount: DiscountModel?) {
        discount?.title?.let {
            binding.itemSecondaryTitle.setTextWithContentDescription(it)
        }
    }

    private fun showDiscountedPrice(price: Double?, discount: DiscountModel?) {
        discount?.let {
            binding.itemDiscountPrice.text = String.format("$ %s", price.toString())
            binding.itemDiscountPrice.paintFlags =
                binding.itemDiscountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } ?: run {
            binding.itemDiscountPrice.visibility = GONE
        }
    }

    private fun showDiscountImage(discount: DiscountModel?) {
        discount?.let {
            binding.itemImageDiscount.apply {
                setAnimation(R.raw.discount)
                repeatCount = LottieDrawable.INFINITE
                playAnimation()
                visibility = View.VISIBLE
            }
        } ?: run {
            binding.itemImageDiscount.visibility = GONE
        }
    }

    private fun showImage(icon: String?) {
        icon?.let {
            binding.itemImage.apply {
                Glide.with(context)
                    .asBitmap()
                    .load(it)
                    .into(this)

                clipToOutline = true
            }
        }
    }

    private fun showTitle(name: String?) {
        name?.let {
            binding.itemTitle.setTextWithContentDescription(it)
        }
    }

    private fun showPrice(price: Double?, discount: DiscountModel?) {
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
        binding.itemPrice.setTextWithContentDescription(String.format("$ %s", price))
        binding.itemDiscountPrice.visibility = visibility
    }
}
