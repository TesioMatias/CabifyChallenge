package com.matiastesio.cabify.ui.cart.list

import androidx.recyclerview.widget.DiffUtil
import com.matiastesio.cabify.data.model.CartItemModel

class CartDiffUtilsCallback(
    val oldItems: MutableList<CartItemModel>,
    val newItems: MutableList<CartItemModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItemCode = oldItems.getOrNull(oldItemPosition)?.code ?: false
        val oldItemName = oldItems.getOrNull(oldItemPosition)?.name ?: false
        val oldItemPrice = oldItems.getOrNull(oldItemPosition)?.price ?: false
        val oldItemDiscount = oldItems.getOrNull(oldItemPosition)?.discount ?: false
        val oldItemDiscountType = oldItems.getOrNull(oldItemPosition)?.discountType ?: false
        val oldItemQuantity = oldItems.getOrNull(oldItemPosition)?.quantity ?: false

        val newItemCode = newItems.getOrNull(newItemPosition)?.code ?: false
        val newItemName = oldItems.getOrNull(newItemPosition)?.name ?: false
        val newItemPrice = oldItems.getOrNull(newItemPosition)?.price ?: false
        val newItemDiscount = oldItems.getOrNull(newItemPosition)?.discount ?: false
        val newItemDiscountType = oldItems.getOrNull(newItemPosition)?.discountType ?: false
        val newItemQuantity = oldItems.getOrNull(newItemPosition)?.quantity ?: false

        return oldItemCode == newItemCode
                && oldItemName == newItemName
                && oldItemPrice == newItemPrice
                && oldItemDiscount == newItemDiscount
                && oldItemDiscountType == newItemDiscountType
                && oldItemQuantity == newItemQuantity
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems.getOrNull(oldItemPosition)
        val newItem = newItems.getOrNull(newItemPosition)
        return objectAreEqual(oldItem, newItem)
    }

    private fun objectAreEqual(oldItem: CartItemModel?, newItem: CartItemModel?): Boolean {
        if (oldItem == null || newItem == null) {
            return oldItem === newItem
        }
        return oldItem == newItem
    }
}
