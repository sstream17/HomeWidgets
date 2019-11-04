package com.stream.homewidgets

import android.content.Context

class WidgetPreferences(context: Context) {
    val PREFS_FILENAME = "com.stream.homewidgets.prefs"
    val PREF_PARKING = "Preference_Parking"
    val PREF_SHAVING = "Preference_Shaving"

    val preferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    fun getParkingFloor(): String? {
        return preferences.getString(PREF_PARKING, "First")
    }

    fun setParkingFloor(floor: String) {
        val editor = preferences.edit()
        editor.putString(PREF_PARKING, floor)
        editor.apply()
    }

    fun getShavingCount(): Int {
        return preferences.getInt(PREF_SHAVING, 1)
    }

    fun setShavingCount(floor: Int) {
        val editor = preferences.edit()
        editor.putInt(PREF_SHAVING, floor)
        editor.apply()
    }
}