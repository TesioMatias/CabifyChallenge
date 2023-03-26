package com.matiastesio.cabify.core.di

import com.matiastesio.cabify.data.db.cart.CartDaoMapper
import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.db.catalog.CatalogDaoMapper
import com.matiastesio.cabify.data.db.catalog.DaoCatalogMapper
import com.matiastesio.cabify.data.db.discount.DaoDiscountMapper
import com.matiastesio.cabify.data.db.discount.DiscountDaoMapper
import com.matiastesio.cabify.data.db.product.DaoProductMapper
import com.matiastesio.cabify.data.db.product.ProductDaoMapper
import com.matiastesio.cabify.data.mapper.catalog.CatalogMapper
import com.matiastesio.cabify.data.mapper.catalog.CatalogMapperImpl
import com.matiastesio.cabify.data.mapper.discount.DiscountMapper
import com.matiastesio.cabify.data.mapper.discount.DiscountMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun bindCatalogMapper(): CatalogMapper {
        return CatalogMapperImpl()
    }

    @Singleton
    @Provides
    fun bindDiscountMapper(): DiscountMapper {
        return DiscountMapperImpl()
    }

    @Singleton
    @Provides
    fun bindProductDaoMapper(): DaoProductMapper {
        return ProductDaoMapper()
    }

    @Singleton
    @Provides
    fun bindDiscountDaoMapper(): DaoDiscountMapper {
        return DiscountDaoMapper()
    }

    @Singleton
    @Provides
    fun bindCatalogDaoMapper(): DaoCatalogMapper {
        return CatalogDaoMapper()
    }

    @Singleton
    @Provides
    fun bindCartDaoMapper(): DaoCartMapper {
        return CartDaoMapper()
    }
}