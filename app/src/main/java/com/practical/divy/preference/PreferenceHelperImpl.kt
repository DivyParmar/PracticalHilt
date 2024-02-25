package com.practical.divy.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Base64
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferenceHelper {

    private val stringId = context.applicationInfo.labelRes
    private val sharedPreferences = context.getSharedPreferences(
        context.getString(stringId) + "_SharedPreferences",
        MODE_PRIVATE
    )

    override fun <T> setValue(key: String, value: T) {
        sharedPreferences.edit {
            putString(key, encodeValue(value.toString()))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue(key: String, defaultValue: T): T {
        val encodedValue = sharedPreferences.getString(key, defaultValue.toString())
        val value = if (!encodedValue.isNullOrBlank() && encodedValue != defaultValue.toString()) {
            decodeValue(encodedValue)
        } else {
            defaultValue.toString()
        }
        return when (defaultValue) {
            is Boolean -> value.toBoolean()
            is Int -> value.toInt()
            is Long -> value.toLong()
            is Float -> value.toFloat()
            else -> value
        } as T
    }

    override fun clearAll(vararg keysToExclude: String) {
        sharedPreferences.edit {
            if (keysToExclude.isEmpty()) {
                clear()
            } else {
                sharedPreferences.all.keys.forEach { key ->
                    if (!keysToExclude.contains(key)) {
                        remove(key)
                    }
                }
            }
        }
    }

    override fun contains(key: String) = sharedPreferences.contains(key)

    private fun encodeValue(value: String): String {
        return Base64.encodeToString(value.toByteArray(), Base64.DEFAULT)
    }

    private fun decodeValue(encodedValue: String): String {
        return String(Base64.decode(encodedValue, Base64.DEFAULT))
    }
}