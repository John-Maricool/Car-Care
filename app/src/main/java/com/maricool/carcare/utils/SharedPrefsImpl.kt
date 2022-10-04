package com.maricool.carcare.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsImpl
    @Inject constructor(var prefs: SharedPreferences) {

    fun getSavedCarId(): Int {
        return prefs.getInt("SAVED_CAR", 0)
    }

    fun saveACarId(id: Int){
        prefs.edit().putInt("SAVED_CAR", id).apply()
    }

}