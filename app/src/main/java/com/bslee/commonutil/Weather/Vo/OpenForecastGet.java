package com.bslee.commonutil.Weather.Vo;

import java.util.List;

/**
 * Created by bslee on 2017-04-27.
 */

public class OpenForecastGet {
    String code;//내부 파라미터
    double message;//내부 파라미터
    WeatherCityItem city;//
    int cnt;
    List<WeatherForecastItem> list;

    public WeatherCityItem getCity() {
        return city;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherForecastItem> getList() {
        return list;
    }
}
