package com.panjiazhi.mdmusic.common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.panjiazhi.mdmusic.MyApplication
import kotlin.reflect.KProperty

class MyPreference<T>(private val keyName: String, private val default: T) {

    private val prefs: SharedPreferences by lazy { MyApplication.instance.getSharedPreferences(keyName, Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharePreferences(keyName, default)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharePreferences(keyName, value)
    }

    private fun putSharePreferences(name: String, value: T) = prefs.edit {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getSharePreferences(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default) as String
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Type Error, cannot be saved!")
        }
        return res as T
    }

}