package com.matiastesio.cabify.ui.cart

import com.matiastesio.cabify.ui.catalog.list.ItemsDiffUtilsCallback
import org.junit.Test

class CartDiffUtilsCallbackTest {

    lateinit var cartDiffUtilsCallback: ItemsDiffUtilsCallback

    @Test
    fun `given correct init then return correct new list size`() {
        // Given
        cartDiffUtilsCallback = ItemsDiffUtilsCallback(
            CartDataSource.getListOldItemsOne,
            CartDataSource.getListNewItemsOne
        )

        // When
        val size = cartDiffUtilsCallback.newListSize

        // Then
        assert(size == 1)
    }

    @Test
    fun `given correct init then return correct old list size`() {
        // Given
        cartDiffUtilsCallback = ItemsDiffUtilsCallback(
            CartDataSource.getListOldItemsOne,
            CartDataSource.getListNewItemsOne
        )

        // When
        val size = cartDiffUtilsCallback.oldListSize

        // Then
        assert(size == 1)
    }

    @Test
    fun `given init with same lists then return equals`() {
        // Given
        cartDiffUtilsCallback = ItemsDiffUtilsCallback(
            CartDataSource.getListOldItemsOne,
            CartDataSource.getListOldItemsOne
        )

        // When
        val equals = cartDiffUtilsCallback.areItemsTheSame(0, 0)

        // Then
        assert(equals)
    }

    @Test
    fun `given init with different lists then return not equals`() {
        // Given
        cartDiffUtilsCallback = ItemsDiffUtilsCallback(
            CartDataSource.getListOldItemsOne,
            CartDataSource.getListNewItemsOne
        )

        // When
        val equals = cartDiffUtilsCallback.areItemsTheSame(0, 0)

        // Then
        assert(!equals)
    }

    @Test
    fun `given init with same lists then return content equals`() {
        // Given
        cartDiffUtilsCallback = ItemsDiffUtilsCallback(
            CartDataSource.getListOldItemsOne,
            CartDataSource.getListOldItemsOne
        )

        // When
        val equals = cartDiffUtilsCallback.areItemsTheSame(0, 0)

        // Then
        assert(equals)
    }

    @Test
    fun `given init with different lists then return content not equals`() {
        // Given
        cartDiffUtilsCallback = ItemsDiffUtilsCallback(
            CartDataSource.getListOldItemsOne,
            CartDataSource.getListNewItemsOne
        )

        // When
        val equals = cartDiffUtilsCallback.areContentsTheSame(0, 0)

        // Then
        assert(!equals)
    }
}