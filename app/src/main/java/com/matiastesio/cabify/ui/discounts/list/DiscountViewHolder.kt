package com.matiastesio.cabify.ui.discounts.list

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.matiastesio.cabify.R
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.databinding.DiscountViewHolderBinding
import com.matiastesio.cabify.ui.discounts.list.builder.DiscountPriceBuilder
import com.matiastesio.cabify.utils.setTextWithContentDescription

class DiscountViewHolder(
    private val binding: DiscountViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(discountModel: DiscountModel) {
        setCode(discountModel.code)
        setTitle(discountModel.title)
        setAfterQuantity(discountModel.afterQty)
        setPrice(discountModel.discountType, discountModel.discount)
    }

    private fun setPrice(discountType: String?, discount: Double?) {
        discount?.let {
            val price = DiscountPriceBuilder
                .create().withDiscountType(discountType)
                .withDiscountPrice(it)
                .build()
            binding.discountPrice.setTextWithContentDescription(price)
        }
    }

    private fun setAfterQuantity(afterQty: Int?) {
        binding.discountAppliesAfterQty.apply {
            afterQty?.let {
                setTextWithContentDescription(
                    String.format(context.getString(R.string.afterr_qty_base_text), afterQty)
                )
                visibility = VISIBLE
            } ?: run {
                visibility = GONE
            }
        }
    }

    private fun setTitle(title: String?) {
        binding.discountTitle.apply {
            title?.let {
                setTextWithContentDescription(it)
                visibility = VISIBLE
            } ?: run {
                visibility = GONE
            }
        }
    }

    private fun setCode(code: String?) {
        binding.discountCode.apply {
            code?.let {
                setTextWithContentDescription(
                    String.format(context.getString(R.string.base_discount_code_text), code)
                )
                visibility = VISIBLE
            } ?: run {
                visibility = GONE
            }
        }
    }
}
