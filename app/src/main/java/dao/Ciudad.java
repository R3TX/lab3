package dao;
import com.google.gson.annotations.SerializedName;
/**
 * Created by r3tx on 13/09/16.
 */
public class Ciudad {
    Coord coord;
    Weather[] weather;
    String base;
    Main main;

    public Wind getWind() {
        return wind;
    }

    public String getBase() {
        return base;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getCod() {
        return cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getDt() {
        return dt;
    }

    public String getId() {
        return id;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public Rain getRain() {
        return rain;
    }

    public Sys getSys() {
        return sys;
    }

    public Weather[] getWeather() {
        return weather;
    }

    Wind wind;
    Clouds clouds;
    Rain rain;
    String dt, id, name, cod;
    Sys sys;


}
