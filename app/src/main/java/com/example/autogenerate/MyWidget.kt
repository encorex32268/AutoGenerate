package com.example.autogenerate

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.autogenerate.MainActivity.Companion.getDate

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
                val name = context.getSharedPreferences("Data", Context.MODE_PRIVATE).getString("name","")
                val fever = arrayListOf<Double>(36.3,36.4,36.5,36.6)
                val randomer  = fever.random()
                val message = "$name 报告如下 \n" +
                        "${getDate()} \n" +
                        "1.是否咳簌，流鼻涕：否 \n" +
                        "2.是否咽喉痛：否 \n" +
                        "3.是否发热：否 \n" +
                        "4.是否有其他身体不适：否 \n" +
                        "5.目前体温：$randomer℃"
                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("MyReport",message)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(context,"Copy Done " , Toast.LENGTH_SHORT).show()
            }

        }

    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    //1. create remote view
    val remoteViews = RemoteViews(context.packageName, R.layout.my_widget)

    //2. define intent --> action which will be performed
    val intent = Intent(context,MyWidget::class.java).apply {
        action = MyWidget.MYWIDGET_ID
    }
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    //3. set pending intent on view
    remoteViews.setOnClickPendingIntent(R.id.openWebButton, pendingIntent)
    //4. update the widget
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}