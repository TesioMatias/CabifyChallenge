package com.matiastesio.cabify.ui.details

import com.matiastesio.cabify.data.model.CatalogItemModel

object DetailDataSource {
    val getDetail: CatalogItemModel = getItemModel()

    private fun getItemModel(): CatalogItemModel {
        return CatalogItemModel(
            code = "code",
            name = "namem",
            price = 10.0,
            icon = "icon",
            null
        )
    }
}