package com.ninogenio.helloappwidget;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by gentra on 23/04/16.
 */
public class DataWidgetIntentService extends IntentService {

    public static final String TAG = DataWidgetIntentService.class.getSimpleName();

    public DataWidgetIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Retrieve all widgets
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        // Get all widget ids from the Homescreen based on the DataWidgetProvider class
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, DataWidgetProvider.class));

        for (int widgetId : appWidgetIds) {
            int randomNumber = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_data);
            remoteViews.setTextViewText(R.id.widget_text, String.valueOf(randomNumber));

            // Register onClickListener, update the widget on click
            Intent newIntent = new Intent(this, DataWidgetProvider.class);
            newIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            newIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_text, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }
}
