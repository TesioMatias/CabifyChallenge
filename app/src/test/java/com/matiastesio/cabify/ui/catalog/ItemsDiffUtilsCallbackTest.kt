package com.matiastesio.cabify.ui.catalog

import com.matiastesio.cabify.ui.catalog.list.ItemsDiffUtilsCallback
import org.junit.Test

class ItemsDiffUtilsCallbackTest {

    lateinit var itemsDiffUtilsCallback: ItemsDiffUtilsCallback

    @Test
    fun `given correct init then return correct new list size`() {
        // Given
        itemsDiffUtilsCallback = ItemsDiffUtilsCallback(
            CatalogTestDataSource.getListOldItemsOne,
            CatalogTestDataSource.getListNewItemsOne
        )

        // When
        val size = itemsDiffUtilsCallback.newListSize

        // Then
        assert(size == 1)
    }

    @Test
    fun `given correct init then return correct old list size`() {
        // Given
        itemsDiffUtilsCallback = ItemsDiffUtilsCallback(
            CatalogTestDataSource.getListOldItemsOne,
            CatalogTestDataSource.getListNewItemsOne
        )

        // When
        val size = itemsDiffUtilsCallback.oldListSize

        // Then
        assert(size == 1)
    }

    @Test
    fun `given init with same lists then return equals`() {
        // Given
        itemsDiffUtilsCallback = ItemsDiffUtilsCallback(
            CatalogTestDataSource.getListOldItemsOne,
            CatalogTestDataSource.getListOldItemsOne
        )

        // When
        val equals = itemsDiffUtilsCallback.areItemsTheSame(0,0)

        // Then
        assert(equals)
    }

    @Test
    fun `given init with different lists then return not equals`() {
        // Given
        itemsDiffUtilsCallback = ItemsDiffUtilsCallback(
            CatalogTestDataSource.getListOldItemsOne,
            CatalogTestDataSource.getListNewItemsOne
        )

        // When
        val equals = itemsDiffUtilsCallback.areItemsTheSame(0,0)

        // Then
        assert(!equals)
    }

    @Test
    fun `given init with same lists then return content equals`() {
        // Given
        itemsDiffUtilsCallback = ItemsDiffUtilsCallback(
            CatalogTestDataSource.getListOldItemsOne,
            CatalogTestDataSource.getListOldItemsOne
        )

        // When
        val equals = itemsDiffUtilsCallback.areItemsTheSame(0,0)

        // Then
        assert(equals)
    }

    @Test
    fun `given init with different lists then return content not equals`() {
        // Given
        itemsDiffUtilsCallback = ItemsDiffUtilsCallback(
            CatalogTestDataSource.getListOldItemsOne,
            CatalogTestDataSource.getListNewItemsOne
        )

        // When
        val equals = itemsDiffUtilsCallback.areContentsTheSame(0,0)

        // Then
        assert(!equals)
    }
}