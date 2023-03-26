package com.matiastesio.cabify.core.di

import com.matiastesio.cabify.BuildConfig
import com.matiastesio.cabify.data.db.cart.CartDao
import com.matiastesio.cabify.data.db.catalog.CatalogDao
import com.matiastesio.cabify.data.db.catalog.DaoCatalogMapper
import com.matiastesio.cabify.data.db.discount.DiscountDao
import com.matiastesio.cabify.data.db.product.ProductDao
import com.matiastesio.cabify.data.network.service.discount.DiscountApi
import com.matiastesio.cabify.data.network.service.discount.DiscountService
import com.matiastesio.cabify.data.network.service.discount.DiscountServiceImpl
import com.matiastesio.cabify.data.network.service.product.ProductApi
import com.matiastesio.cabify.data.network.service.product.ProductService
import com.matiastesio.cabify.data.network.service.product.ProductServiceImpl
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import com.matiastesio.cabify.data.repositories.cart.CartRepositoryImpl
import com.matiastesio.cabify.data.repositories.catalog.CatalogRepository
import com.matiastesio.cabify.data.repositories.catalog.CatalogRepositoryImpl
import com.matiastesio.cabify.data.repositories.discount.DiscountRepository
import com.matiastesio.cabify.data.repositories.discount.DiscountRepositoryImpl
import com.matiastesio.cabify.data.repositories.product.ProductRepository
import com.matiastesio.cabify.data.repositories.product.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBaseClient(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideProductService(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDiscountService(retrofit: Retrofit): DiscountApi {
        return retrofit.create(DiscountApi::class.java)
    }

    @Singleton
    @Provides
    fun bindProductService(
        api: ProductApi
    ): ProductService {
        return ProductServiceImpl(api)
    }

    @Singleton
    @Provides
    fun bindDiscountService(
        api: DiscountApi
    ): DiscountService {
        return DiscountServiceImpl(api)
    }

    @Singleton
    @Provides
    fun bindProductRepository(
        api: ProductService,
        productDao: ProductDao
    ): ProductRepository {
        return ProductRepositoryImpl(api, productDao)
    }

    @Singleton
    @Provides
    fun bindDiscountRepository(
        api: DiscountService,
        discountDao: DiscountDao
    ): DiscountRepository {
        return DiscountRepositoryImpl(api, discountDao)
    }

    @Singleton
    @Provides
    fun bindCatalogRepository(
        catalogDao: CatalogDao,
        catalogDaoMapper: DaoCatalogMapper
    ): CatalogRepository {
        return CatalogRepositoryImpl(catalogDao, catalogDaoMapper)
    }

    @Singleton
    @Provides
    fun bindCartRepository(
        cartDao: CartDao,
    ): CartRepository {
        return CartRepositoryImpl(cartDao)
    }
}
