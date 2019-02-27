package com.example.mywidgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RandomNumbersWidget extends AppWidgetProvider {
	
	void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
								int appWidgetId) {
		
		// Initiate text
		String lastUpdate = "Random: " + NumberGenerator.Generate(100);
		// Construct the RemoteViews object, komponen tsb berguna utk mengambil data layout dari widget
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_numbers_widget);
		// Set remoteviews text view text content from TextView id in Widget context
		views.setTextViewText(R.id.appwidget_text, lastUpdate); // Parameternya adalah : id xml dr widget sm text dr String value
		// Set pending intent ke Button di Widget pada saat OnClick event
		views.setOnClickPendingIntent(R.id.btn_click, getPendingSelfIntent(context, appWidgetId, WIDGET_CLICK));
		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	// Method tsb brguna ktika widget pertama kali dibuat atau dijalankan ketika updatePeriod yg ad di Widget xml
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
	
	private static String WIDGET_CLICK = "widgetsclick";
	
	// Method tsb berguna untuk menerima hasil dari broadcast oleh PendingIntent
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		if(WIDGET_CLICK.equals(intent.getAction())){ // Cek jika static WIDGET_CLICK itu sama kyk action dari Intent di PendingIntent
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.random_numbers_widget); // Buat view widget
			String lastUpdate = "Random: " + NumberGenerator.Generate(100);
			int appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0); // Dptin value appWidgetId dari Intent data
			views.setTextViewText(R.id.appwidget_text, lastUpdate); // Update text view di random_numbers_widget xml (bawaan dari RemoteViews)
			appWidgetManager.updateAppWidget(appWidgetId, views); // Update widget
		}
	}
	
	private static String WIDGET_ID_EXTRA = "widget_id_extra";
	
	protected PendingIntent getPendingSelfIntent(Context context, int appWidgetId, String action){
		Intent intent = new Intent(context, getClass());
		intent.setAction(action); // Set action dari intent bedasarkan parameter action
		intent.putExtra(WIDGET_ID_EXTRA, appWidgetId); // Bawa data appWidgetId (bawaan dari updateAppWidget method) ke Intent
		return PendingIntent.getBroadcast(context, appWidgetId, intent, 0); // Lakukan broadcast di PendingIntent
	}
	
	
}

