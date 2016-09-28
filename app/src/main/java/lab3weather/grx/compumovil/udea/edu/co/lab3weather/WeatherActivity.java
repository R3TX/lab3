package lab3weather.grx.compumovil.udea.edu.co.lab3weather;

import android.app.DialogFragment;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//import broadcast.PedirDatos;
import dao.Ciudad;
import dao.Sys;

public class WeatherActivity extends AppCompatActivity {
    private static TextView ciudad;
    private static TextView descripcion;
    private static TextView temperatura;
    private static TextView humedad;
    private  TextView lastView;
    private static ImageView weatherIcon;
    private PedirDatos pedirDatos;
    private static Ciudad ciudadDao;
    private Button buton;
    static SharedPreferences pref;
    final static String CIUDAD_PREF = "CIUDAD";


    EditText et;
    String etStr;
    /*
    private Ciudad ciudadDao;
    private Gson gson;
    private RequestQueue requestQueue;
    private String url;
    private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final String key = "&lang=es&units=metric&appid=56ba148cc5691288d5479fa498159bd6";
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        crearComponentes();
        pref = getSharedPreferences("mypref",MODE_PRIVATE);
        pedirDatos =PedirDatos.getInstance(this);

        pedirDatos.fetchPosts();
        buton = (Button) findViewById(R.id.bot);


/*
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        requestQueue = Volley.newRequestQueue(this);//iniciamos el nuevo que
        crearUrl();
        */
    }

    public void cambiar ( View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        TextView tv = new TextView(this);
        tv.setText("Cambiar ciudad");
        tv.setPadding(40, 40, 40, 40);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(20);

        et = new EditText(this);
        etStr = et.getText().toString();

        alertDialogBuilder.setView(et);
        alertDialogBuilder.setTitle("cambiar ciudad");
        alertDialogBuilder.setMessage("cambiar ciudad");
        alertDialogBuilder.setCustomTitle(tv);

        //if (isError)
            alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        // alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);

        // Setting Negative "Cancel" Button
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        // Setting Positive "Yes" Button
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                       etStr = et.getText().toString();
                        if(!etStr.trim().equals("")) {


                            pref.edit().putString(CIUDAD_PREF, etStr).commit();
                            pedirDatos.fetchPosts();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        try {
            alertDialog.show();

        } catch (Exception e) {
            // WindowManager$BadTokenException will be caught and the app would
            // not display the 'Force Close' message
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        crearComponentes();
        pedirDatos =PedirDatos.getInstance(this);
        pedirDatos.fetchPosts();
        //ciudadDao = pedirDatos.getCiudad();
        //formatear();
    /*    requestQueue = Volley.newRequestQueue(this);
        crearUrl();
        crearComponentes();
        fetchPosts();
*/
    }
/*
    public void crearUrl() {
        url = baseUrl + "London,uk" + key;  //generamo la nueva url

    }

    private void fetchPosts() {

        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, null);//generamos el request y que hacer dependiendo de la respuesta
        requestQueue.add(request);//hacemos el request
    }


    private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {//si la url puede devolver algo se llama esto
            //List<Ciudad> posts = Arrays.asList(gson.fromJson(response, Ciudad[].class)); de estas dos formas podemos obtener arreglos
            //Type collectionType = new TypeToken<Collection<Ciudad>>(){}.getType();
           // Collection<Ciudad> enums = gson.fromJson(response, collectionType);
            ciudadDao = gson.fromJson(response, Ciudad.class);
            formatear();



        }
    };
    Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap bitmap) {
            weatherIcon.setImageBitmap(bitmap);

        }
    };
    */

    public void crearComponentes(){
        ciudad= (TextView) findViewById(R.id.weatherName) ;

        descripcion = (TextView) findViewById(R.id.descripcion);

        temperatura =(TextView) findViewById(R.id.temper);

        humedad = (TextView) findViewById(R.id.humedad);

        lastView = (TextView) findViewById(R.id.lastView);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);


    }

    public  static void formatear() {
        ciudad.setText("Ciudad: "+ciudadDao.getName());
        descripcion.setText("Clima: "+ciudadDao.getWeather()[0].getDescription());
        temperatura.setText("Temperatura: "+Float.toString(ciudadDao.getMain().getTemp())+"°");
        humedad.setText("Humedad: "+Float.toString(ciudadDao.getMain().getHumidity())+"%");
        /*String url = "http://openweathermap.org/img/w/" + ciudadDao.getWeather()[0].getIcon()+".png";
        ImageRequest imageRequest = new ImageRequest(url,listener,0,0, Bitmap.Config.ALPHA_8,null);
        requestQueue.add(imageRequest);
*/
    }





    /*
    * Broadcast Reciever
    *
    * */
    public class Reboot extends BroadcastReceiver {
        SharedPreferences sharedPreferences;
        //PedirDatos

        public void onReceive(Context context, Intent intent) {
            sharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);




        }
    }


    /*
    * Pedir datos
    *
    *
    * */


    public static class PedirDatos {
        //private ImageView imageView;
        private static PedirDatos pedirDatos = null;
        //private Ciudad ciudadDao;
        private Gson gson;
        private RequestQueue requestQueue;
        private String url;
        private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
        private final String key = "&lang=es&units=metric&appid=56ba148cc5691288d5479fa498159bd6";
        Context context;


        private PedirDatos(Context context) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("d/m/yy hh:mm a");
            gson = gsonBuilder.create();
            requestQueue = Volley.newRequestQueue(context);//iniciamos el nuevo que
            //imageView = (ImageView) context.;
            this.context= context;


            fetchPosts();
        }

        public static PedirDatos getInstance(Context context) {
            if (pedirDatos == null) {
                pedirDatos = new PedirDatos(context);
            }
            return pedirDatos;
        }

        private void crearUrl() {
            String ciudad = pref.getString(CIUDAD_PREF,"");
            if (ciudad.equals("")){
                ciudad = "london,uk";
            }
            url = baseUrl +  ciudad + key;  //generamo la nueva url
            Log.d("Crearurl   ",url+"   ");

        }

        public Ciudad getCiudad() {
            return ciudadDao;
        }

        private void fetchPosts() {
            crearUrl();

            StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, error);//generamos el request y que hacer dependiendo de la respuesta
            requestQueue.add(request);//hacemos el request
        }
        private final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pref.edit().putString(CIUDAD_PREF,"london,uk").commit();
            }

        };

        private final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//si la url puede devolver algo se llama esto
                //List<Ciudad> posts = Arrays.asList(gson.fromJson(response, Ciudad[].class)); de estas dos formas podemos obtener arreglos
                //Type collectionType = new TypeToken<Collection<Ciudad>>(){}.getType();
                // Collection<Ciudad> enums = gson.fromJson(response, collectionType);
                Log.e("JSON", response);
                if(response==null||response.equals("")||response.contains("Error")){
                    pref.edit().putString(CIUDAD_PREF,"london,uk").commit();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("La ciudad ingresada es vacia o invalida, \n por favor omita caracteres especiales como las tildes");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else {
                    ciudadDao = gson.fromJson(response, Ciudad.class);
                    String url = "http://openweathermap.org/img/w/" + ciudadDao.getWeather()[0].getIcon() + ".png";
                    ImageRequest imageRequest = new ImageRequest(url, listener, 0, 0, Bitmap.Config.ALPHA_8, null);
                    requestQueue.add(imageRequest);
                    formatear();
                }


            }
        };
        private final Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                weatherIcon.setImageBitmap(bitmap);

            }
        };

    }
    /*
    *
    *
    * Widget
    ****
    */
    public class Widget extends AppWidgetProvider {
        PedirDatos pedirDatos=null;
        Ciudad ciudad = null;


        @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


            ComponentName thiswidget = new ComponentName(context,Widget.class);
            int [] all = appWidgetManager.getAppWidgetIds(thiswidget);
            final int count = appWidgetIds.length;
           // for(int i = 0; i<count;i++){
            for(int widgetId:all){
                //int widgetId=appWidgetIds[i];
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.id.ejemplo);
                View view;
                view = new View(context);
                ImageView imageView = (ImageView) view.findViewById(R.id.weatherIcon);
                pedirDatos = PedirDatos.getInstance(context);
                ciudad = pedirDatos.getCiudad();
                remoteViews.setTextViewText(R.id.weatherName,ciudadDao.getName() );
                remoteViews.setTextViewText(R.id.descripcion, ciudadDao.getWeather()[0].getDescription());
                remoteViews.setTextViewText(R.id.temper, Float.toString(ciudadDao.getMain().getTemp())+"°");
                remoteViews.setTextViewText(R.id.humedad, Float.toString(ciudadDao.getMain().getHumidity())+"%");
                Intent intent = new Intent(context, WeatherActivity.class);
                intent.setAction(appWidgetManager.ACTION_APPWIDGET_UPDATE);
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.humedad, pendingIntent);
                appWidgetManager.updateAppWidget(widgetId,remoteViews);
            }
        }
    }


}
