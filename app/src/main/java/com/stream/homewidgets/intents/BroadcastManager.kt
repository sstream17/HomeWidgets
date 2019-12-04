package com.stream.homewidgets.intents

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.stream.homewidgets.Parking
import com.stream.homewidgets.WidgetPreferences


class BroadcastManager: BroadcastReceiver() {

    lateinit var preferences: WidgetPreferences

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals("com.stream.homewidgets.UPDATE_PARKING")){
            val currentFloor = intent?.getStringExtra("Floor_Parked")
            broadcastParkingIntent(context, currentFloor)
        }
    }

    fun broadcastParkingIntent(context: Context?, floor: String?)
    {
        context?.let {
            preferences = WidgetPreferences(it)
            val componentName = ComponentName(it, Parking::class.java)
            val intent = Intent(context, Parking::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val ids = AppWidgetManager.getInstance(it).getAppWidgetIds(componentName)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            intent.putExtra("Floor_Parked", floor)
            it.sendBroadcast(intent)
            preferences.setParkingFloor(floor!!)
        }
    }
}