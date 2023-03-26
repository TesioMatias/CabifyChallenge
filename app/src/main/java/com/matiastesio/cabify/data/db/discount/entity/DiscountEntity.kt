package com.matiastesio.cabify.data.db.discount.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "discount_table")
data class DiscountEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "afterQty") val afterQty: Int,
    @ColumnInfo(name = "discount") val discount: Double,
    @ColumnInfo(name = "discountType") val discountType: String
)

fun DiscountEntity.toDatabase() = DiscountEntity(
    code = code,
    title = title,
    afterQty = afterQty,
    discount = discount,
    discountType = discountType
)
