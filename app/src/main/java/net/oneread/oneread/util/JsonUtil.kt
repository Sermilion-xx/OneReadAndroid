package net.oneread.oneread.util

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonSyntaxException

/**
 * Created by sermilion on 9/12/17.
 */

object JsonUtil {
    private lateinit var gson: Gson

    val defaultGson: Gson
        get() {
            if (gson == null) {
                gson = Gson()
            }
            return gson
        }

    fun toJson(`object`: Any): String = defaultGson.toJson(`object`)

    @Throws(JsonSyntaxException::class)
    fun <T> fromJson(json: String, classOfT: Class<T>): T = defaultGson.fromJson(json, classOfT)

    fun childToString(element: JsonElement?, childName: String): String? {
        if (element == null || element.isJsonNull) {
            return null
        }
        val child = element.asJsonObject.get(childName)
        return if (child == null || child.isJsonNull) {
            null
        } else child.asString
    }
}