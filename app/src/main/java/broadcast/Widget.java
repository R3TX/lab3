/*

package broadcast;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;


import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;

import dao.Ciudad;
import lab3weather.grx.compumovil.udea.edu.co.lab3weather.R;
import lab3weather.grx.compumovil.udea.edu.co.lab3weather.WeatherActivity;

/**
 * Created by r3tx on 20/09/16.
 *
public class Widget extends AppWidgetProvider{
    PedirDatos pedirDatos=null;
    Ciudad ciudad = null;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        final int count = appWidgetIds.length;
        for(int i = 0; i<count;i++){
            int widgetId=appWidgetIds[i];
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_weather);
            View view;
            view = new View(context);
            ImageView imageView = (ImageView) view.findViewById(R.id.weatherIcon);
            pedirDatos = PedirDatos.getInstance(context, imageView);
            ciudad = pedirDatos.getCiudad();
            remoteViews.setTextViewText(R.id.weatherName,ciudad.getName() );
            remoteViews.setTextViewText(R.id.descripcion, ciudad.getWeather()[0].getDescription());
            remoteViews.setTextViewText(R.id.temper, Float.toString(ciudad.getMain().getTemp())+"°");
            remoteViews.setTextViewText(R.id.humedad, Float.toString(ciudad.getMain().getHumidity())+"%");
            Intent intent = new Intent(context, WeatherActivity.class);
            intent.setAction(appWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);
        }
    }
}
*/