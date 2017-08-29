package com.bslee.commonutil.Weather.Vo;

import java.util.List;

/**
 * Created by bslee on 2017-04-28.
 */

public class WeatherForecastItem {
    double dt; //예보시간 UTC
    WeatherMainItem main;
    List<WeatherItem> weather; //예보추가 정보
    WeatherCloudsItem clouds; //구름정보
    WeatherWindItem wind;//바람정보
    WeatherRainSnow rain;//비정보
    WeatherRainSnow snow;//눈정보
    String dt_txt;//시간 포멧 변경 UTC

    public double getDt() {
        return dt;
    }

    public WeatherMainItem getMain() {
        return main;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public WeatherCloudsItem getClouds() {
        return clouds;
    }

    public WeatherWindItem getWind() {
        return wind;
    }

    public WeatherRainSnow getRain() {
        return rain;
    }

    public WeatherRainSnow getSnow() {
        return snow;
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
