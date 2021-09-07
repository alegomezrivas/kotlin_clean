package com.softstark.hackernews.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softstark.hackernews.cache.db.CacheConstants

@Entity(tableName = CacheConstants.NEWS_TABLE_NAME)
data class CachedNews(

    @PrimaryKey
    @ColumnInfo(name = "news_id")
    var newsId: Int,

    @ColumnInfo(name = "author")
    var author: String? = "",

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "createAt")
    var createAt: String? = "",

    @ColumnInfo(name = "url")
    var url: String? = "",

    )
