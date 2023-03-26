package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.db.catalog.DaoCatalogMapper
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.repositories.catalog.CatalogRepository
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val daoCatalogMapper: DaoCatalogMapper
) {
    suspend operator fun invoke(code: String): CatalogItemModel {
        val response = catalogRepository.getCatalogEntity(code)
        return daoCatalogMapper.fromEntity(response)
    }
}
