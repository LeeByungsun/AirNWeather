package com.bslee.commonutil;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bslee.commonutil.AirDust.AirAPI;
import com.bslee.commonutil.GPS.GpsAPI;
import com.bslee.commonutil.Geo.GeoCoderAPI;
import com.bslee.commonutil.Geo.GeoPoint;
import com.bslee.commonutil.Geo.GeoTrans;
import com.bslee.commonutil.Weather.OpenWeatherMapAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bslee.commonutil.Constant.RENEW_GPS;
import static com.bslee.commonutil.Constant.SEND_PRINT;

public class MainActivity extends AppCompatActivity {
    private static String TAG = Constant.APP_NAME + " Mainactivity";
    public Handler mHandler;
    GpsAPI gps = null;
    private EditText editText;
    private Button btnShowLocation;
    private String mAddress;
    private double mLat;
    private double mLon;
    private AirAPI mAirAPI;
    private OpenWeatherMapAPI mWeatherAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        mAirAPI = AirAPI.getInstance(this);
        mWeatherAPI = OpenWeatherMapAPI.getInstance(this);
        editText = (EditText) findViewById(R.id.editText);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == RENEW_GPS) {
                    makeNewGpsService();
                }
                if (msg.what == SEND_PRINT) {
                    logPrint((String) msg.obj);
                }
            }
        };
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // create class object
                GPSStart();

            }
        });
        GPSStart();
        GeoTransTest();
    }

    public void makeNewGpsService() {
        if (gps == null) {
            gps = new GpsAPI(MainActivity.this, mHandler);
        } else {
            gps.Update();
        }

    }

    public void logPrint(String str) {
        String address = GeoCoderAPI.getAddress(this, mLat, mLon);
        editText.append(getTimeStr() + " " + str + " " + address + "\n");
    }

    public String getTimeStr() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("MM/dd HH:mm:ss");
        return sdfNow.format(date);
    }

    public void GeoTransTest() {
        int page = 4;
        int numOfRow = 20;
        String ver = "1.3";
        GeoPoint in_pt = new GeoPoint(mLon, mLat);
        Log.e(TAG, "geo in : xGeo=" + in_pt.getX() + ", yGeo=" + in_pt.getY());

        GeoPoint tm_pt = GeoTrans.convert(GeoTrans.GEO, GeoTrans.TM, in_pt);
        Log.e(TAG, "tm : xTM=" + tm_pt.getX() + ", yTM=" + tm_pt.getY());
        GeoPoint katec_pt = GeoTrans.convert(GeoTrans.TM, GeoTrans.KATEC, tm_pt);
        Log.e(TAG, "katec : xKATEC=" + katec_pt.getX() + ", yKATEC=" + katec_pt.getY());
        GeoPoint out_pt = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, katec_pt);
        Log.e(TAG, "geo out : xGeo=" + out_pt.getX() + ", yGeo=" + out_pt.getY());
        GeoPoint in2_pt = new GeoPoint(128., 38.);
        Log.e(TAG, "geo distance between (127,38) and (128,38) =" + GeoTrans.getDistancebyGeo(in_pt, in2_pt) + "km");

//        mAirAPI.getNearbyMsrstnList(tm_pt.getX(), tm_pt.getY(), page, numOfRow);
//        mAirAPI.getMsrstnList("서울", "", page, numOfRow);
//        mAirAPI.getTMStdrCrdnt("혜화동");
//        mAirAPI.getMsrstnAcctoRltmMesureDnsty("종로구", "DAILY", ver, page, numOfRow);
//        mAirAPI.getUnityAirEnvrnIdexSnstiveAboveMsrstnList(page, numOfRow); //테스트 다시 해야 함
//        mAirAPI.getCtprvnRltmMesureDnsty("서울", ver, page, numOfRow);
//        ver = "1.1";
//        mAirAPI.getMinuDustFrcstDspth("PM10", "2017-04-26", ver, page, numOfRow);
//        mAirAPI.getCtprvnMesureLIst("PM25","HOUR","",page,numOfRow); //리턴값 이상 확인 해봐야 함
//        mAirAPI.getCtprvnMesureSidoLIst("서울", "HOUR", page, numOfRow);//리턴값 이상 확인 해봐야 함
//        mAirAPI.getMsrstnAcctoLastDcsnDnsty("종로구", "DAILY", page, numOfRow);

//        mAirAPI.getDatePollutnStatInfo("2008-1", "도시대기", page, numOfRow);

//        mAirAPI.getOzAdvsryOccrrncInfo("2008", page, numOfRow);
//        mAirAPI.getYlwsndAdvsryOccrrncInfo("2008", page, numOfRow);
//        mWeatherAPI.getWeatherBygeoCoordi(mLat, mLon);
        mWeatherAPI.getForecastBygeoCoordi(mLat, mLon);
    }

    private void GPSStart() {
        if (gps == null) {
            gps = new GpsAPI(MainActivity.this, mHandler);
        } else {
            gps.Update();
        }

        // check if GPS enabled
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            mLat = latitude;
            mLon = longitude;
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }


}
