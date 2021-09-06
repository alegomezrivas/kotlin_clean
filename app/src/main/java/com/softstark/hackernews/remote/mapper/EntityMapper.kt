package com.softstark.hackernews.remote.mapper

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
