package com.songs.data

import com.airhireme.data.preferences.PreferenceManager

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

object DataManager {

    fun clearAllPreferenceData() {
        PreferenceManager.clearAllPrefs()
    }
}