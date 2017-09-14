package net.oneread.oneread.onereadauth.account

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.util.LruCache
import com.google.gson.Gson
import net.oneread.oneread.util.JsonUtil
import java.lang.reflect.Type

/**
 * Created by sermilion on 9/12/17.
 */

abstract class BasePersistentStorage<T>(mContext: Context) {

    private val mSharedPreferences: SharedPreferences
    private val mGson: Gson
    private val cache = LruCache<String, T>(10)

    abstract fun getName(): String
    abstract fun getType(): Type

    init {
        mSharedPreferences = mContext.getSharedPreferences(
                getName(),
                Context.MODE_PRIVATE
        )
        mGson = JsonUtil.getDefaultGson()
    }

    fun put(key: String, value: T) {
        val json = mGson.toJson(value)
        mSharedPreferences.edit()
                .putString(key, json)
                .apply()

        cache.put(cacheKey(key), value)
    }

    fun remove(key: String) {
        mSharedPreferences.edit().remove(key).apply()
        cache.remove(key)
    }

    operator fun contains(key: String): Boolean = mSharedPreferences.contains(key)

    operator fun get(key: String?): T? {
        var value: T = cache.get(cacheKey(key))

        if (value == null) {
            val json = mSharedPreferences.getString(key, null)
            if (json != null) {
                value = mGson.fromJson<T>(json, getType())
                cache.put(cacheKey(key), value!!)
            }
        }

        return value
    }

    private fun cacheKey(key: String?): String = key ?: "_anonymous_"
}
