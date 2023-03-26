package com.matiastesio.cabify.ui.discounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.matiastesio.cabify.R
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.databinding.DiscountsFragmentBinding
import com.matiastesio.cabify.ui.discounts.list.DiscountAdapter
import com.matiastesio.cabify.utils.startViewWithAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscountsFragment : Fragment() {

    private val discountViewModel: DiscountsViewModel by viewModels()
    private lateinit var binding: DiscountsFragmentBinding
    private lateinit var discountAdapter: DiscountAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DiscountsFragmentBinding.inflate(layoutInflater)
        binding.progressLoader.startViewWithAnimation(R.raw.progress)
        setObservers()
        setList()
        loadData()
        setCatalogButton()
        setCartButton()
        return binding.root
    }

    private fun loadData() {
        discountViewModel.getDiscounts()
    }

    private fun setList() {
        discountAdapter = DiscountAdapter()
        binding.discountsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = discountAdapter
        }
    }

    private fun setObservers() {
        discountViewModel.discounts.observe(viewLifecycleOwner) {
            setDiscounts(it)
        }

        discountViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.discountsRecyclerView.visibility = View.GONE
                binding.goToCatalogButton.visibility = View.GONE
                binding.progressLoader.apply {
                    visibility = View.VISIBLE
                    playAnimation()
                }
            } else {
                binding.progressLoader.apply {
                    pauseAnimation()
                    visibility = View.GONE
                }
                binding.discountsRecyclerView.visibility = View.VISIBLE
                binding.goToCatalogButton.visibility = View.VISIBLE
            }
        }
    }

    private fun setDiscounts(discounts: List<DiscountModel>) {
        if (discounts.isEmpty()) {
            binding.progressLoader.startViewWithAnimation(R.raw.empylist)
            binding.discountsRecyclerView.visibility = View.GONE
            binding.emptyStateTitle.visibility = View.VISIBLE
        } else {
            binding.discountsRecyclerView.visibility = View.VISIBLE
            binding.emptyStateTitle.visibility = View.GONE
            discountAdapter.setItems(discounts.toMutableList())
        }
    }

    private fun setCartButton() {
        binding.goToCartButton.setOnClickListener {
            val action = DiscountsFragmentDirections.actionDiscountsFragmentToCartFragment()
            findNavController().navigate(action, null)
        }
    }

    private fun setCatalogButton() {
        binding.goToCatalogButton.setOnClickListener {
            val action = DiscountsFragmentDirections.actionDiscountsFragmentToCatalogFragment()
            findNavController().navigate(action, null)
        }
    }
}
