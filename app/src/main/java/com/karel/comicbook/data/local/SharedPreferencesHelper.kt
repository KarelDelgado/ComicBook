package com.karel.comicbook.data.local

import android.content.SharedPreferences

class SharedPreferencesHelper(private val sharedPrefs: SharedPreferences) {

    fun put(key: String, value: String) {
        sharedPrefs.edit().putString(key, value).apply()
    }

    fun putCommit(key: String, value: Boolean) {
        //only use for instances when app is about to be restarted
        sharedPrefs.edit().putBoolean(key, value).commit()
    }

    fun putCommit(key: String, value: String) {
        //only use for instances when app is about to be restarted
        sharedPrefs.edit().putString(key, value).commit()
    }

    fun put(key: String, value: Int) {
        sharedPrefs.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Float) {
        sharedPrefs.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Long) {
        sharedPrefs.edit().putLong(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        sharedPrefs.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String, defaultValue: String): String? {
        return sharedPrefs.getString(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Int): Int {
        return sharedPrefs.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float {
        return sharedPrefs.getFloat(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Long): Long {
        return sharedPrefs.getLong(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Boolean): Boolean {
        return sharedPrefs.getBoolean(key, defaultValue)
    }

    fun deleteSavedData(key: String) {
        sharedPrefs.edit().remove(key).apply()
    }

    fun clear() {
        sharedPrefs.edit().clear().commit()
    }
}