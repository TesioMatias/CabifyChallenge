package com.matiastesio.cabify.ui.discount

import com.matiastesio.cabify.ui.discounts.list.DiscountsDiffUtilsCallback
import org.junit.Test

class DiscountsDiffUtilsCallbackTest {

    lateinit var discountsDiffUtilsCallback: DiscountsDiffUtilsCallback

    @Test
    fun `given correct init then return correct new list size`() {
        // Given
        discountsDiffUtilsCallback = DiscountsDiffUtilsCallback(
            DiscountDataSource.getListOldItemsOne,
            DiscountDataSource.getListNewItemsOne
        )

        // When
        val size = discountsDiffUtilsCallback.newListSize

        // Then
        assert(size == 1)
    }

    @Test
    fun `given correct init then return correct old list size`() {
        // Given
        discountsDiffUtilsCallback = DiscountsDiffUtilsCallback(
            DiscountDataSource.getListOldItemsOne,
            DiscountDataSource.getListNewItemsOne
        )

        // When
        val size = discountsDiffUtilsCallback.oldListSize

        // Then
        assert(size == 1)
    }

    @Test
    fun `given init with same lists then return equals`() {
        // Given
        discountsDiffUtilsCallback = DiscountsDiffUtilsCallback(
            DiscountDataSource.getListOldItemsOne,
            DiscountDataSource.getListOldItemsOne
        )

        // When
        val equals = discountsDiffUtilsCallback.areItemsTheSame(0, 0)

        // Then
        assert(equals)
    }

    @Test
    fun `given init with different lists then return not equals`() {
        // Given
        discountsDiffUtilsCallback = DiscountsDiffUtilsCallback(
            DiscountDataSource.getListOldItemsOne,
            DiscountDataSource.getListNewItemsOne
        )

        // When
        val equals = discountsDiffUtilsCallback.areItemsTheSame(0, 0)

        // Then
        assert(!equals)
    }

    @Test
    fun `given init with same lists then return content equals`() {
        // Given
        discountsDiffUtilsCallback = DiscountsDiffUtilsCallback(
            DiscountDataSource.getListOldItemsOne,
            DiscountDataSource.getListOldItemsOne
        )

        // When
        val equals = discountsDiffUtilsCallback.areItemsTheSame(0, 0)

        // Then
        assert(equals)
    }

    @Test
    fun `given init with different lists then return content not equals`() {
        // Given
        discountsDiffUtilsCallback = DiscountsDiffUtilsCallback(
            DiscountDataSource.getListOldItemsOne,
            DiscountDataSource.getListNewItemsOne
        )

        // When
        val equals = discountsDiffUtilsCallback.areContentsTheSame(0, 0)

        // Then
        assert(!equals)
    }
}