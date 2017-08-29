package com.bslee.commonutil.Weather.Vo;

/**
 * Created by bslee on 2017-04-28.
 */

public class WeatherMainItem {

    double temp; //온도
    double pressure;//기압 hPa
    double humidity;//습도 %
    double temp_min;//최저기온
    double temp_max;//최고기온
    double sea_level;//수면기준 기압
    double grnd_level;//지면 기준 기압

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getSea_level() {
        return sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }
}
