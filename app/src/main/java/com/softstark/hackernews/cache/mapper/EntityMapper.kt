package com.softstark.hackernews.cache.mapper

interface EntityMapper<T, V> {

    fun mapFromCached(type: T): V

    fun mapToCached(type: V): T
}