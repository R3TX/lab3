package dao;

/**
 * Created by r3tx on 16/09/16.
 */
public class Weather {
    int id;
    String main;
    String description;
    String icon;

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }
}
