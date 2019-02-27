package com.example.mywidgets;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.widget.RemoteViews;

public class UpdatingWidgetService extends JobService {
	// Method tsb berguna agar JobService mulai melakukan jobnya
	@Override
	public boolean onStartJob(JobParameters jobParameters) {
		AppWidgetManager manager = AppWidgetManager.getInstance(this); // dapatin object manager dr smua available Widget
		RemoteViews view = new RemoteViews(getPackageName(), R.layout.random_numbers_widget);
		ComponentName theWidget = new ComponentName(this, RandomNumbersWidget.class); // code ini berguna untuk mendapatkan nama class Widget
		String lastUpdate = "Random: " + NumberGenerator.Generate(100);
		view.setTextViewText(R.id.appwidget_text, lastUpdate);
		manager.updateAppWidget(theWidget, view); // Update semua Widget yg ada krn parameter nya adalah ComponentName, bkn id dr Widget
		jobFinished(jobParameters, false); // Callback method dr JobManager bahwa jobnya itu telah selesai dilakukan
		return true;
	}
	
	@Override
	public boolean onStopJob(JobParameters jobParameters) {
		return false;
	}
}
