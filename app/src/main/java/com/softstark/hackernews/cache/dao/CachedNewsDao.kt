package com.softstark.hackernews.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softstark.hackernews.cache.models.CachedNews

@Dao
interface CachedNewsDao {

    @Query("SELECT * FROM news")
    fun getNews(): List<CachedNews>

    @Query("DELETE FROM news")
    fun clearNews()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNews(cachedNews: CachedNews)
}