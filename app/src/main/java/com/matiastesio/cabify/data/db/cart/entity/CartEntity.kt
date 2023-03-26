package com.matiastesio.cabify.data.db.cart.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "afterQty") val afterQty: Int,
    @ColumnInfo(name = "discount") val discount: Double,
    @ColumnInfo(name = "discountType") val discountType: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
)

fun CartEntity.toDatabase() = CartEntity(
    code = code,
    name = name,
    price = price,
    icon = icon,
    title = title,
    afterQty = afterQty,
    discount = discount,
    discountType = discountType,
    quantity = quantity
)