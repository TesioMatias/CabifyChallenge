package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.db.discount.DaoDiscountMapper
import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.repositories.discount.DiscountRepository
import javax.inject.Inject

class GetDiscountsUseCase @Inject constructor(
    private val discountRepository: DiscountRepository,
    private val daoMapper: DaoDiscountMapper
) {

    suspend operator fun invoke(): List<DiscountEntity> {
        val response = discountRepository.getDiscountsFromRemote()
        var result = daoMapper.mapToDao(response?.discounts)
        if (result.isEmpty()) {
            result = discountRepository.getDiscountsFromLocal()
        } else {
            discountRepository.clearDiscounts()
            discountRepository.insertDiscounts(result)
        }

        return result
    }
}
