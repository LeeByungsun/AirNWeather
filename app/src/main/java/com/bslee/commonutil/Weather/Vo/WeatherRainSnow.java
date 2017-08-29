package com.bslee.commonutil.Weather.Vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bslee on 2017-04-28.
 */

public class WeatherRainSnow {
    @SerializedName("3h")
    double h; //3시간 강수량

    public double getH() {
        return h;
    }
}
