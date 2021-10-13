package com.example.autogenerate.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.autogenerate.R
import com.example.autogenerate.uitls.ReportCreator

/**
 * Implementation of App Widget functionality.
 */
class MyWidget : AppWidgetProvider() {
    companion object{
        const val MYWIDGET_ID = "copy"
    }
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == MYWIDGET_ID){
            context?.let{
                ReportCreator(it).apply { intentShareToWeChat() }
            }
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val remoteViews = RemoteViews(context.packageName, R.layout.my_widget)
    val intent = Intent(context, MyWidget::class.java).apply {
        action = MyWidget.MYWIDGET_ID
    }
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    remoteViews.setOnClickPendingIntent(R.id.openWebButton, pendingIntent)

    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}