package com.matiastesio.cabify.data.repositories.catalog

import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.model.CatalogItemModel

interface CatalogRepository {
    suspend fun store(list: List<CatalogItemModel>)
    suspend fun getCatalogEntity(code: String): CatalogEntity
}