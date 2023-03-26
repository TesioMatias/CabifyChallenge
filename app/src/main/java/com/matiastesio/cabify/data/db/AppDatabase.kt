package com.matiastesio.cabify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matiastesio.cabify.data.db.cart.CartDao
import com.matiastesio.cabify.data.db.cart.entity.CartEntity
import com.matiastesio.cabify.data.db.catalog.CatalogDao
import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.db.discount.DiscountDao
import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.db.product.ProductDao
import com.matiastesio.cabify.data.db.product.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, DiscountEntity::class, CatalogEntity::class, CartEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getDiscountDao(): DiscountDao
    abstract fun getCatalogDao(): CatalogDao
    abstract fun getCartDao(): CartDao
}