package com.matiastesio.cabify.ui.cart.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.databinding.CartViewHolderBinding

class CartAdapter(
    private var items: MutableList<CartItemModel> = mutableListOf()
) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: CartViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun setItems(newItems: MutableList<CartItemModel>) {
        val diffUtilsCallback = CartDiffUtilsCallback(items, newItems)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilsCallback)

        items.clear()
        items.addAll(newItems)

        diffUtilResult.dispatchUpdatesTo(this)
    }
}
