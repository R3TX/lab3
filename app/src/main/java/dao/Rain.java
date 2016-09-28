package dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by r3tx on 16/09/16.
 */
public class Rain {
    @SerializedName("3h")
    float h3;

    public float getH3() {
        return h3;
    }
}
