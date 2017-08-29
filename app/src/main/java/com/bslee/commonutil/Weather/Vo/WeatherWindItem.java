package com.bslee.commonutil.Weather.Vo;

/**
 * Created by bslee on 2017-04-28.
 */

public class WeatherWindItem {
    double speed;//풍속 meter/sec
    double deg;//풍향 degrees
    double gust;//돌풍

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    public double getGust() {
        return gust;
    }
}
