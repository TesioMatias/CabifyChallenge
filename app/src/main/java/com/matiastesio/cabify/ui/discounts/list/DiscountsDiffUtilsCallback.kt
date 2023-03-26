package com.matiastesio.cabify.ui.discounts.list

import androidx.recyclerview.widget.DiffUtil
import com.matiastesio.cabify.data.model.DiscountModel


class DiscountsDiffUtilsCallback(
    private val oldList: List<DiscountModel>,
    private val newList: List<DiscountModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItemCode = oldList.getOrNull(oldItemPosition)?.code ?: false
        val oldItemTitle = oldList.getOrNull(oldItemPosition)?.title ?: false
        val oldItemAfterQty = oldList.getOrNull(oldItemPosition)?.afterQty ?: false
        val oldItemDiscountType = oldList.getOrNull(oldItemPosition)?.discountType ?: false
        val oldItemDiscount = oldList.getOrNull(oldItemPosition)?.discount ?: false
        val newItemCode = newList.getOrNull(newItemPosition)?.code ?: false
        val newItemTitle = newList.getOrNull(newItemPosition)?.title ?: false
        val newItemAfterQty = newList.getOrNull(newItemPosition)?.afterQty ?: false
        val newItemDiscountType = newList.getOrNull(newItemPosition)?.discountType ?: false
        val newItemDiscount = newList.getOrNull(newItemPosition)?.discount ?: false

        return oldItemCode == newItemCode
                && oldItemTitle == newItemTitle
                && oldItemAfterQty == newItemAfterQty
                && oldItemDiscountType == newItemDiscountType
                && oldItemDiscount == newItemDiscount
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition)
        val newItem = newList.getOrNull(newItemPosition)
        return objectAreEqual(oldItem, newItem)
    }

    private fun objectAreEqual(oldItem: DiscountModel?, newItem: DiscountModel?): Boolean {
        if (oldItem == null || newItem == null) {
            return oldItem === newItem
        }
        return oldItem == newItem
    }
}
