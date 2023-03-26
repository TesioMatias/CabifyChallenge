package com.matiastesio.cabify.ui.discounts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.databinding.DiscountViewHolderBinding

class DiscountAdapter(
    private var items: MutableList<DiscountModel> = mutableListOf()
) : RecyclerView.Adapter<DiscountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
        val binding =
            DiscountViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscountViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: DiscountViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun setItems(newItems: MutableList<DiscountModel>) {
        val diffUtilsCallback = DiscountsDiffUtilsCallback(items, newItems)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilsCallback)

        items.clear()
        items.addAll(newItems)

        diffUtilResult.dispatchUpdatesTo(this)
    }
}
