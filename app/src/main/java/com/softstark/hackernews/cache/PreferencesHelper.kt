package com.softstark.hackernews.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
@Singleton
open class PreferencesHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_BUFFER_PACKAGE_NAME = "com.softstark.hackernews.cache.preferences"

        private const val PREF_KEY_ADD_EXCEPTIONS = "add_exceptions_cache"

        private const val PREF_KEY_LIST_EXCEPTIONS = "list_exceptions_cache"
    }

    private val bufferPref: SharedPreferences =
        context.getSharedPreferences(PREF_BUFFER_PACKAGE_NAME, Context.MODE_PRIVATE)

    /**
     * Store and retrieve the last time data was cached
     */
    var addExceptionsCached: Int
        get() = bufferPref.getInt(PREF_KEY_ADD_EXCEPTIONS, 0)
        set(exceptionsCached) = bufferPref.edit().putInt(PREF_KEY_ADD_EXCEPTIONS, exceptionsCached)
            .apply()

    var listExceptionsCached: ArrayList<String?>?
        get() = getArrayList()
        set(exceptionsCached) = saveArrayList(exceptionsCached, PREF_KEY_LIST_EXCEPTIONS)


    fun saveArrayList(list: ArrayList<String?>?, key: String?) {
        val editor: SharedPreferences.Editor = bufferPref.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    private fun getArrayList(): ArrayList<String?>? {
        val gson = Gson()
        val json: String? = bufferPref.getString(PREF_KEY_LIST_EXCEPTIONS, null)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(json, type)
    }
}
