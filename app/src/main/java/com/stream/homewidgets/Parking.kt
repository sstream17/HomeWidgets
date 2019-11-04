package com.stream.homewidgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.content.Intent
import android.util.Log

/**
 * Implementation of App Widget functionality.
 */
class Parking : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            val appWidgetIds = intent?.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS)
            val currentFloor = intent?.getStringExtra("Floor_Parked")
            context?.let {
                val remoteViews = RemoteViews(it.packageName, R.layout.parking)
                val appWidgetManager = AppWidgetManager.getInstance(it)
                appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
                // There may be multiple widgets active, so update all of them
                for (appWidgetId in appWidgetIds!!) {
                    updateAppWidget(context, appWidgetManager, appWidgetId, currentFloor)
                }
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Enter relevant functionality for when the widget is updated
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
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
    val widgetText = "$parkingIcon $currentFloor"
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.parking)
    views.setTextViewText(R.id.parking_widget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}