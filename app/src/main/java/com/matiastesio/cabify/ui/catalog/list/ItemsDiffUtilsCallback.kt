package com.matiastesio.cabify.ui.catalog.list

import androidx.recyclerview.widget.DiffUtil
import com.matiastesio.cabify.data.model.CatalogItemModel

class ItemsDiffUtilsCallback(
    private val oldList: List<CatalogItemModel>,
    private val newList: List<CatalogItemModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItemCode = oldList.getOrNull(oldItemPosition)?.code ?: false
        val oldItemName = oldList.getOrNull(oldItemPosition)?.name ?: false
        val oldItemPrice = oldList.getOrNull(oldItemPosition)?.price ?: false
        val newItemCode = newList.getOrNull(newItemPosition)?.code ?: false
        val newItemName = newList.getOrNull(newItemPosition)?.name ?: false
        val newItemPrice = newList.getOrNull(newItemPosition)?.price ?: false
        return oldItemName == newItemName && oldItemCode == newItemCode && oldItemPrice == newItemPrice
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition)
        val newItem = newList.getOrNull(newItemPosition)
        return objectAreEqual(oldItem, newItem)
    }

    private fun objectAreEqual(oldItem: CatalogItemModel?, newItem: CatalogItemModel?): Boolean {
        if (oldItem == null || newItem == null) {
            return oldItem === newItem
        }
        return oldItem == newItem
    }
}
