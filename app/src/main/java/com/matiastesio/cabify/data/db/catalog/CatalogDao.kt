package com.matiastesio.cabify.data.db.catalog

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity

@Dao
interface CatalogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(models: List<CatalogEntity>)

    @Query("SELECT * FROM catalog_table WHERE code = :code")
    suspend fun getDetailById(code: String): CatalogEntity
}