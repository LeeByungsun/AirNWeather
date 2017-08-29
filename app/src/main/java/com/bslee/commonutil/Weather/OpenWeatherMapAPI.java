package com.bslee.commonutil.Weather;

import android.content.Context;
import android.util.Log;

import com.bslee.commonutil.Constant;
import com.bslee.commonutil.Weather.Vo.OpenForecastGet;
import com.bslee.commonutil.Weather.Vo.OpenWeatherGet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bslee on 2017-04-27.
 */

public class OpenWeatherMapAPI {
    public static String TAG = " OpenWeatherMapAPI";
    private static OpenWeatherMapAPI _weatherApi = null;
    public final OpenWeatherMapAPIInfo weatherInfo;
    private final Retrofit retrofit;
    Gson gson;
    //    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//    OkHttpClient client = new OkHttpClient();
//    client.interceptors().add(loggingInterceptor);
    HttpLoggingInterceptor loggingInterceptor;
    OkHttpClient.Builder client;
    private Context mCtx;

    public OpenWeatherMapAPI(Context ctx) {
        mCtx = ctx;
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setPrettyPrinting() // Pretty print
                .create();
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder();
        client.addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder().baseUrl(OpenWeatherMapAPIInfo.WeatherAPI)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        weatherInfo = retrofit.create(OpenWeatherMapAPIInfo.class);
    }

    public static OpenWeatherMapAPI getInstance(Context ctx) {

        if (_weatherApi == null) {
            _weatherApi = new OpenWeatherMapAPI(ctx);
        }
        return _weatherApi;
    }


    public void getWeatherBygeo(double lat, double lon) {
        Call<OpenWeatherGet> call = weatherInfo.getWeatherBygeoCoordi(lat, lon, Constant.AppId, "metric");
        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<OpenWeatherGet>() {
            @Override
            public void onResponse(Call<OpenWeatherGet> call, Response<OpenWeatherGet> response) {

                Log.i(TAG, "도시 : " + response.body().getName());
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<OpenWeatherGet> call, Throwable t) {
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + t.getMessage());
            }
        });
    }

    public void getForecastBygeoCoordi(double lat, double lon) {
        Call<OpenForecastGet> call = weatherInfo.getForecastBygeoCoordi(lat, lon, Constant.AppId, "metric");
        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<OpenForecastGet>() {
            @Override
            public void onResponse(Call<OpenForecastGet> call, Response<OpenForecastGet> response) {
                int size = response.body().getCnt();
                Log.i(TAG, "도시 이름 :" + response.body().getCity().getName());
                Log.i(TAG, "나라코드 :" + response.body().getCity().getCountry());
                for (int i = 0; i < size; i++) {

                    Log.i(TAG, "시간 :" + response.body().getList().get(i).getDt_txt());
                    Log.i(TAG, "온도 :" + response.body().getList().get(i).getMain().getTemp());
                    Log.i(TAG, "예보 :" + response.body().getList().get(i).getWeather().get(0).getDescription());
                    Log.i(TAG, "날씨 :" + response.body().getList().get(i).getWeather().get(0).getMain());
                    Log.i(TAG, "날씨ID :" + response.body().getList().get(i).getWeather().get(0).getId());

                }

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<OpenForecastGet> call, Throwable t) {
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + t.getMessage());
            }
        });
    }
}
