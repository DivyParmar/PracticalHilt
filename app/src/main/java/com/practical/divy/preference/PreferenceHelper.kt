package com.practical.divy.preference

interface PreferenceHelper {
    fun <T> setValue(key: String, value: T)
    fun <T> getValue(key: String, defaultValue: T): T
    fun clearAll(vararg keysToExclude: String)
    fun contains(key: String): Boolean
}