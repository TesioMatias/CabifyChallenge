package com.matiastesio.cabify.data.db.catalog

import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.model.CatalogItemModel

interface DaoCatalogMapper {
    fun toEntity(model: List<CatalogItemModel>): List<CatalogEntity>
    fun fromEntity(entity: CatalogEntity): CatalogItemModel
}