package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.repositories.catalog.CatalogRepository
import javax.inject.Inject

class StoreCatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke(list: List<CatalogItemModel>) =
        catalogRepository.store(list)
}
