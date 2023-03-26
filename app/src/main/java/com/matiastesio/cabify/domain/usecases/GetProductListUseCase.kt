package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.db.product.DaoProductMapper
import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.repositories.product.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val daoMapper: DaoProductMapper
) {
    suspend operator fun invoke(): List<ProductEntity> {
        val response = productRepository.getProductsFromRemote()
        var result = daoMapper.mapToDao(response?.products)
        if (result.isEmpty()) {
            result = productRepository.getProductsFromLocal()
        } else {
            productRepository.clearProducts()
            productRepository.insertProducts(result)
        }

        return result
    }
}
