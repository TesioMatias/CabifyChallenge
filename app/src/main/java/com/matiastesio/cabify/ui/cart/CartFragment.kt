package com.matiastesio.cabify.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matiastesio.cabify.R
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.databinding.CartFragmentBinding
import com.matiastesio.cabify.ui.cart.list.CartAdapter
import com.matiastesio.cabify.utils.pricestrategy.ItemPriceStrategy
import com.matiastesio.cabify.utils.setTextWithContentDescription
import com.matiastesio.cabify.utils.startViewWithAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var binding: CartFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CartFragmentBinding.inflate(layoutInflater)
        setObservers()
        binding.progressLoader.startViewWithAnimation(R.raw.progress)
        setList()
        loadData()
        setClearCartButton()
        setProceedToCheckoutButton()
        return binding.root
    }

    private fun setList() {
        cartAdapter = CartAdapter()
        binding.cartList.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cartAdapter
        }
    }

    private fun loadData() {
        cartViewModel.getCart()
    }

    private fun setObservers() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) {
            setCartItems(it)
        }

        cartViewModel.doReturnToCatalog.observe(viewLifecycleOwner) {
            if (it) {
                pushCatalog()
            }
        }

        cartViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.cartList.visibility = View.GONE
                binding.proceedToCheckout.visibility = View.GONE
                binding.progressLoader.apply {
                    visibility = View.VISIBLE
                    playAnimation()
                }
            } else {
                binding.progressLoader.apply {
                    pauseAnimation()
                    visibility = View.GONE
                }
                binding.cartList.visibility = View.VISIBLE
                binding.proceedToCheckout.visibility = View.VISIBLE
            }
        }
    }

    private fun pushCatalog() {
        val action = CartFragmentDirections.actionCartFragmentToCatalogFragment()
        findNavController().navigate(action, null)
    }

    private fun setCartItems(list: List<CartItemModel>?) {
        if (list.isNullOrEmpty()) {
            binding.progressLoader.startViewWithAnimation(R.raw.empylist)
            binding.cartList.visibility = GONE
            binding.totalPrice.visibility = GONE
            binding.totalText.visibility = GONE
            binding.emptyStateTitle.visibility = VISIBLE
        } else {
            binding.cartList.visibility = VISIBLE
            binding.emptyStateTitle.visibility = GONE
            binding.totalPrice.visibility = VISIBLE
            binding.totalText.visibility = VISIBLE
            cartAdapter.setItems(list.toMutableList())
            setTotalPrice(list)
        }
    }

    private fun setTotalPrice(list: List<CartItemModel>) {
        var totalPrice = 0.0
        list.forEach {
            ItemPriceStrategy.setStrategy(it.discountType)
            totalPrice += ItemPriceStrategy.calculatePrice(
                it.price,
                it.discount,
                it.quantity,
                it.afterQty
            )
        }
        binding.totalPrice.setTextWithContentDescription(String.format("$ %.2f", totalPrice))
    }

    private fun setProceedToCheckoutButton() {
        binding.proceedToCheckout.setOnClickListener {
            cartViewModel.payCart()
            val action = CartFragmentDirections.actionCartFragmentToCheckoutFragment()
            findNavController().navigate(action, null)
        }
    }

    private fun setClearCartButton() {
        binding.clearCart.setOnClickListener {
            cartViewModel.clearCart()
        }
    }
}
