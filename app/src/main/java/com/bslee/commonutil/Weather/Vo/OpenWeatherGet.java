package com.bslee.commonutil.Weather.Vo;

import java.util.List;

/**
 * Created by bslee on 2017-04-27.
 */

public class OpenWeatherGet {
    WeatherCoordItem coord;//위경도
    List<WeatherItem> weather; //그룹 날씨 정보
    String base;//내부 파라미터
    WeatherMainItem main; //날씨 정보
    double visibility;
    WeatherWindItem wind; //바람 정보
    WeatherRainSnow rain; //비 최근 3시간
    WeatherRainSnow snow; //눈 최근세시간
    WeatherCloudsItem clouds; //구름
    double dt; //시간 UTC
    WeatherSys sys;
    double id;//국가 ID
    String name;//도시이름
    int cod;//내부 파라미터

    public WeatherCoordItem getCoord() {
        return coord;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public WeatherMainItem getMain() {
        return main;
    }

    public double getVisibility() {
        return visibility;
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

    public WeatherCloudsItem getClouds() {
        return clouds;
    }

    public double getDt() {
        return dt;
    }

    public WeatherSys getSys() {
        return sys;
    }

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
