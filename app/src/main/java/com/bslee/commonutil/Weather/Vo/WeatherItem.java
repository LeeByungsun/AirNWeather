package com.bslee.commonutil.Weather.Vo;

/**
 * Created by bslee on 2017-04-28.
 */

/**
 * 날씨 상태
 * icon URL : http://openweathermap.org/img/w/10d.png
 * id 2xx : thunderstorm
 * id 3xx : Drizzle
 * id 5xx : Rain
 * id 6xx : Snow
 * id 7xx : Atmosphere
 * id 800 : Clear
 * id 80x : Clouds
 * id 90X : Extreme
 * id 9xx : Additional
 * https://openweathermap.org/weather-conditions
 */
public class WeatherItem {
    int id; //날씨 상태 ID
    String main; //그룹의 날씨 상태(Rain, Snow, Extreme etc)
    String description;// 그룹의 날씨 상태 설명
    String icon;//날씨 아이콘 ID

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
