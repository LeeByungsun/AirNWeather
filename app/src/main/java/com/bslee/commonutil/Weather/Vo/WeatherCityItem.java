package com.bslee.commonutil.Weather.Vo;

/**
 * Created by bslee on 2017-04-28.
 */

public class WeatherCityItem {

    double id; //City ID
    String name; // 도시 이름
    WeatherCoordItem coord; //위경도
    String country; //나라 코드

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WeatherCoordItem getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
