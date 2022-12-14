package com.splanes.gifting.data.common.utils.preferences

import android.content.SharedPreferences
import com.google.gson.Gson

fun <T> SharedPreferences.Editor.putJson(key: String, value: T) {
    putString(key, Gson().toJson(value))
}

inline fun <reified T> SharedPreferences.getJson(key: String): T? =
    getString(key, null)?.let { json -> Gson().fromJson(json, T::class.java) }
