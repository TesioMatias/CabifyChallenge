package com.matiastesio.cabify.data.repositories.catalog

import com.matiastesio.cabify.data.db.catalog.CatalogDao
import com.matiastesio.cabify.data.db.catalog.DaoCatalogMapper
import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogDao: CatalogDao,
    private val catalogDaoMapper: DaoCatalogMapper
) : CatalogRepository {
    override suspend fun store(list: List<CatalogItemModel>) =
        catalogDao.store(catalogDaoMapper.toEntity(list))

    override suspend fun getCatalogEntity(code: String): CatalogEntity =
        catalogDao.getDetailById(code)
}
