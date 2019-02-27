package com.example.mywidgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RandomNumbersWidget extends AppWidgetProvider {
	
	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
								int appWidgetId) {
		
		// Initiate text
		String lastUpdate = "Random: " + NumberGenerator.Generate(100);
		// Construct the RemoteViews object, komponen tsb berguna utk mengambil data layout dari widget
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_numbers_widget);
		// Set remoteviews text view text content from TextView id in Widget context
		views.setTextViewText(R.id.appwidget_text, lastUpdate); // Parameternya adalah : id xml dr widget sm text dr String value
		
		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	// Method tsb brguna ktika widget pertama kali dibuat atau dijalankan ketika updatePeriod
	// (min. 30 mins) telah lewat
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		for(int appWidgetId : appWidgetIds) {
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}
	
	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first widget is created
	}
	
	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
	}
}

