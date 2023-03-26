package com.matiastesio.cabify.data.db.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiastesio.cabify.data.db.product.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM product_table ORDER BY code DESC")
    suspend fun getProducts(): List<ProductEntity>

    @Query("DELETE FROM product_table")
    suspend fun deleteProducts()
}