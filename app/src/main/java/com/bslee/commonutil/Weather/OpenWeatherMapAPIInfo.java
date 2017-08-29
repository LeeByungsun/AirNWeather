package com.bslee.commonutil.Weather;

import com.bslee.commonutil.Weather.Vo.OpenForecastGet;
import com.bslee.commonutil.Weather.Vo.OpenWeatherGet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bslee on 2017-04-27.
 */

public interface OpenWeatherMapAPIInfo {


    //server Url
    public static final String WeatherAPI = "http://api.openweathermap.org/data/2.5/";


//    @FormUrlEncoded
//    @POST("weather")
//    Call<ResponseBody> getWeatherBygeoCoordi(@Field("lat") double lat, @Field("lon") double lon, @Field(value = "appid", encoded = true) String apikkey);
    @GET("weather")
    Call<OpenWeatherGet> getWeatherBygeoCoordi(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apikkey, @Query("units") String units);

    @GET("forecast")
    Call<OpenForecastGet> getForecastBygeoCoordi(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apikkey, @Query("units") String units);
}
