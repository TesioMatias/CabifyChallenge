package com.matiastesio.cabify.data.db.product.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "icon") val icon: String
)

fun ProductEntity.toDatabase() = ProductEntity(
    code = code,
    name = name,
    price = price,
    icon = icon
)