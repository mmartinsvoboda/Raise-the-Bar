package com.mmartinsvoboda.sporttrackingapp.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun <T> T.toGsonString(
    pretty: Boolean = false
): String {
    return if (pretty) {
        GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(this)
    } else {
        Gson().toJson(this)
    }
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type
inline fun <reified T : Any> String.toObject(): T = Gson().fromJson(this, T::class.java)