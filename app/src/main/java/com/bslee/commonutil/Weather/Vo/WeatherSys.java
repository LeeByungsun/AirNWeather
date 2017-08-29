package com.bslee.commonutil.Weather.Vo;

/**
 * Created by bslee on 2017-04-28.
 */

public class WeatherSys {
    int type;//내부 파라미터
    double id;//내부파라미터
    double message;//내부파라미터
    String Country;//국가정보
    long sunrise;//일출 시간 UTC
    long sunset;//일몰 시간 UTC

    public int getType() {
        return type;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }
}
