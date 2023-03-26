package com.matiastesio.cabify.core.di

import android.content.Context
import androidx.room.Room
import com.matiastesio.cabify.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    private const val PRODUCT_DATABASE_NAME = "database"

    @Singleton
    @Provides
    fun provideProductRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, PRODUCT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProductDao(
        db: AppDatabase
    ) = db.getProductDao()

    @Singleton
    @Provides
    fun provideDiscountDao(
        db: AppDatabase
    ) = db.getDiscountDao()

    @Singleton
    @Provides
    fun provideCatalogDao(
        db: AppDatabase
    ) = db.getCatalogDao()

    @Singleton
    @Provides
    fun provideCartDao(
        db: AppDatabase
    ) = db.getCartDao()
}
