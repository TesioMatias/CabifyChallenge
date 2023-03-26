package com.matiastesio.cabify.ui.catalog.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.databinding.ProductViewHolderBinding

class CatalogAdapter(
    private var items: MutableList<CatalogItemModel> = mutableListOf(),
    private var onClickHandler: OnClickHandler
) : RecyclerView.Adapter<CatalogItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemViewHolder {
        val binding =
            ProductViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: CatalogItemViewHolder, position: Int) {
        viewHolder.bind(items[position], onClickHandler)
    }

    fun setItems(newItems: MutableList<CatalogItemModel>) {
        val diffUtilsCallback = ItemsDiffUtilsCallback(items, newItems)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilsCallback)

        items.clear()
        items.addAll(newItems)

        diffUtilResult.dispatchUpdatesTo(this)
    }
}
