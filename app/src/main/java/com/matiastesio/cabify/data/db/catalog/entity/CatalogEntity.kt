package com.matiastesio.cabify.data.db.catalog.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalog_table")
data class CatalogEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "afterQty") val afterQty: Int,
    @ColumnInfo(name = "discount") val discount: Double,
    @ColumnInfo(name = "discountType") val discountType: String,
)

fun CatalogEntity.toDatabase() = CatalogEntity(
    code = code,
    name = name,
    price = price,
    icon = icon,
    title = title,
    afterQty = afterQty,
    discount = discount,
    discountType = discountType
)