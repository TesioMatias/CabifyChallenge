package com.matiastesio.cabify.data.db.discount

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity

@Dao
interface DiscountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiscounts(discounts: List<DiscountEntity>)

    @Query("SELECT * FROM discount_table ORDER BY code DESC")
    suspend fun getDiscounts(): List<DiscountEntity>

    @Query("DELETE FROM discount_table")
    suspend fun deleteDiscounts()
}