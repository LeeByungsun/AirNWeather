package com.bslee.commonutil.Geo;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.bslee.commonutil.Constant;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by bslee on 2017-04-18.
 */

public class GeoCoderAPI {
    public static String TAG = Constant.APP_NAME + " GeoCoder_API";
    /**
     * 위도,경도로 주소구하기
     *
     * @param lat
     * @param lng
     * @return 주소
     */
    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress = "현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress = currentLocationAddress;
                }
            }

        } catch (IOException e) {
            Toast.makeText(mContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return nowAddress;
    }
}
