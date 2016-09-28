/*

package broadcast;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.Ciudad;
import dao.Weather;
import lab3weather.grx.compumovil.udea.edu.co.lab3weather.WeatherActivity;

/**
 * Created by r3tx on 16/09/16.

public class PedirDatos {
    private ImageView imageView;
    private static PedirDatos pedirDatos= null;
    private Ciudad ciudadDao;
    private Gson gson;
    private RequestQueue requestQueue;
    private String url;
    private final String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final String key = "&lang=es&units=metric&appid=56ba148cc5691288d5479fa498159bd6";


    private PedirDatos(Context context, ImageView imageView){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("d/m/yy hh:mm a");
        gson = gsonBuilder.create();
        requestQueue = Volley.newRequestQueue(context);//iniciamos el nuevo que
        this.imageView = imageView;
        crearUrl();
        fetchPosts();
    }
    public static PedirDatos getInstance(Context context, ImageView imageView){
        if(pedirDatos==null){
            pedirDatos=new PedirDatos(context, imageView);
        }
        return pedirDatos;
    }
    private void crearUrl() {
        url = baseUrl + "London,uk" + key;  //generamo la nueva url

    }
    public Ciudad getCiudad(){
        return ciudadDao;
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
            String url = "http://openweathermap.org/img/w/" + ciudadDao.getWeather()[0].getIcon()+".png";
            ImageRequest imageRequest = new ImageRequest(url,listener,0,0, Bitmap.Config.ALPHA_8,null);
            requestQueue.add(imageRequest);
            //formatear();



        }
    };
    private final Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);

        }
    };

}
*/