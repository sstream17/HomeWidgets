package com.stream.homewidgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class Parking : AppWidgetProvider() {

    lateinit var preferences: WidgetPreferences

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        preferences = WidgetPreferences(context)
        val currentFloor = preferences.getParkingFloor()
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, currentFloor)
        }
    }

    override fun onEnabled(context: Context) {
        val componentName = ComponentName(context, Parking::class.java)
        val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(componentName)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        preferences = WidgetPreferences(context)
        val currentFloor = preferences.getParkingFloor()
        for (appWidgetId in ids) {
            updateAppWidget(context, appWidgetManager, appWidgetId, currentFloor)
        }
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    currentFloor: String?
) {
    val parkingIcon = context.getString(R.string.parking_icon)
    val widgetText = "$parkingIcon ${currentFloor ?: ""}"
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.parking)
    views.setTextViewText(R.id.parking_widget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}