package com.softstark.hackernews.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.softstark.hackernews.cache.Migrations
import com.softstark.hackernews.cache.dao.CachedNewsDao
import com.softstark.hackernews.cache.models.CachedNews
import javax.inject.Inject

@Database(entities = [CachedNews::class], version = Migrations.DB_VERSION)
abstract class NewsDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedNewsDao(): CachedNewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, NewsDatabase::class.java, CacheConstants.DB_NAME
        ).build()
    }
}
