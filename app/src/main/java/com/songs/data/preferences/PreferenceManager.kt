package com.airhireme.data.preferences

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.songs.SongsApp

/**
 * Created by {Kapil Sachan} on 17/01/2021.
 */

object PreferenceManager {

    private var sharedPref: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(SongsApp.appContext)

    fun putInt(key: String, value: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int {
        return sharedPref.getInt(key, 0)
    }

    fun putString(key: String, value: String?) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun clearAllPrefs() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun clearViaKey(key: String) {
        sharedPref.edit().remove(key).apply()
    }
}