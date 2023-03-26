package com.matiastesio.cabify.ui.cart.list

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.databinding.CartViewHolderBinding
import com.matiastesio.cabify.utils.pricestrategy.ItemPriceStrategy
import com.matiastesio.cabify.utils.setTextWithContentDescription

class CartViewHolder(
    private val binding: CartViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cartModel: CartItemModel) {
        setImage(cartModel.icon)
        setTitle(cartModel.quantity, cartModel.name)
        setDiscountTitle(cartModel.title)
        setDiscountPrice(cartModel.price, cartModel.quantity, cartModel.discountType)
        setPrice(
            cartModel.price,
            cartModel.quantity,
            cartModel.discountType,
            cartModel.discount,
            cartModel.afterQty
        )
    }

    private fun setPrice(
        price: Double,
        quantity: Int,
        discountType: String,
        discount: Double,
        afterQty: Int
    ) {
        ItemPriceStrategy.setStrategy(discountType)
        val calculatedPrice = ItemPriceStrategy.calculatePrice(price, discount, quantity, afterQty)
        binding.itemPrice.setTextWithContentDescription(String.format("$ %.2f", calculatedPrice))
    }

    private fun setDiscountPrice(price: Double, quantity: Int, discountType: String) {
        if (discountType.isBlank()) {
            binding.itemDiscountPrice.visibility = GONE
        } else {
            binding.itemDiscountPrice.visibility = VISIBLE
            binding.itemDiscountPrice.setTextWithContentDescription(
                String.format("$ %.2f", price * quantity)
            )
        }
    }

    private fun setDiscountTitle(title: String) {
        if (title.isBlank()) {
            binding.discountTitle.visibility = GONE
        } else {
            binding.discountTitle.visibility = VISIBLE
            binding.discountTitle.setTextWithContentDescription(title)
        }
    }

    private fun setTitle(quantity: Int, name: String) {
        binding.itemTitle.setTextWithContentDescription(String.format("%d x %s", quantity, name))
    }

    private fun setImage(icon: String) {
        binding.itemImage.apply {
            Glide.with(context)
                .asBitmap()
                .load(icon)
                .into(this)

            clipToOutline = true
        }
    }
}
