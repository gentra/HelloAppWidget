package com.ninogenio.helloappwidget;

import android.app.PendingIntent;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.Random;

/**
 * Created by gentra on 23/04/16.
 */
public class ListWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDataSetChanged() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public int getCount() {
                // Random List count, 0, 50
                return new Random().nextInt(50);
            }

            @Override
            public RemoteViews getViewAt(int position) {
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.list_widget);
                views.setTextViewText(R.id.widget_text, String.valueOf(new Random().nextInt(100)));

                // Register onClickListener, open the app on click
                Intent newIntent = new Intent();
                views.setOnClickFillInIntent(R.id.row, newIntent);

                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.list_widget);
            }

            @Override
            public int getViewTypeCount() {
                // We only have one type of list here
                return 1;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}
