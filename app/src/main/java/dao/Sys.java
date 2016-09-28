package dao;

/**
 * Created by r3tx on 16/09/16.
 */
public class Sys {
    int type,id, sunrise, sunset;
    float message;
    String country;

    public int getId() {
        return id;
    }

    public float getMessage() {
        return message;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public int getType() {
        return type;
    }

    public String getCountry() {

        return country;
    }
}
