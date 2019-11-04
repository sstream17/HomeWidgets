package com.stream.homewidgets.intents

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.stream.homewidgets.Parking

class BroadcastManager: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        broadcastParkingIntent(context, "Fifth")
    }

    fun broadcastParkingIntent(context: Context?, floor: String?)
    {
        context?.let {
            val componentName = ComponentName(it, Parking::class.java)
            val intent = Intent(context, Parking::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val ids = AppWidgetManager.getInstance(it).getAppWidgetIds(componentName)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            intent.putExtra("Floor_Parked", floor)
            context.sendBroadcast(intent)
        }
    }
}