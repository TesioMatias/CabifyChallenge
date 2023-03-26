package com.matiastesio.cabify.data.db.cart

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiastesio.cabify.data.db.cart.entity.CartEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartEntity)

    @Query("SELECT * FROM cart_table WHERE code = :code")
    suspend fun getCartItemById(code: String): CartEntity?

    @Query("UPDATE cart_table SET quantity = quantity + 1 WHERE code = :code")
    suspend fun updateQuantity(code: String)

    @Query("SELECT * FROM cart_table ORDER BY code DESC")
    suspend fun getCart(): List<CartEntity>?

    @Query("DELETE FROM cart_table")
    suspend fun clearCart()
}