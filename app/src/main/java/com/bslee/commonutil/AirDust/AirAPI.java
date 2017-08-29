package com.bslee.commonutil.AirDust;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bslee.commonutil.AirDust.Vo.ArpltnInforInqireSvc;
import com.bslee.commonutil.AirDust.Vo.ArpltnStatsSvc;
import com.bslee.commonutil.AirDust.Vo.CtprvnMesureList;
import com.bslee.commonutil.AirDust.Vo.CtprvnMesureListSido;
import com.bslee.commonutil.AirDust.Vo.MinuDustFrcstDspth;
import com.bslee.commonutil.AirDust.Vo.MsrstnInfoInqireSvc;
import com.bslee.commonutil.Constant;
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
 * Created by bslee on 2017-04-19.
 */

public class AirAPI {

    private static AirAPI _airApi = null;
    public final AirAPIInfo airapiinfo;
    private final Retrofit retrofit;
    Gson gson;
    //    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//    OkHttpClient client = new OkHttpClient();
//    client.interceptors().add(loggingInterceptor);
    HttpLoggingInterceptor loggingInterceptor;
    OkHttpClient.Builder client;
    private Context mCtx;

    public static String TAG = Constant.APP_NAME + " AirAPI";
    public AirAPI(Context ctx) {
        mCtx = ctx;
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setPrettyPrinting() // Pretty print
                .create();
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder();
        client.addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder().baseUrl(AirAPIInfo.ObserverAPI)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        airapiinfo = retrofit.create(AirAPIInfo.class);
    }

    public static AirAPI getInstance(Context ctx) {

        if (_airApi == null) {
            _airApi = new AirAPI(ctx);
        }
        return _airApi;
    }

    /**
     * 근접 측정소 목록 조회 결과
     *
     * @param tmX
     * @param tmY
     * @param page
     * @param numOfRow
     */
    public void getNearbyMsrstnList(double tmX, double tmY, int page, int numOfRow) {
        Call<MsrstnInfoInqireSvc> call = airapiinfo.getNearbyMsrstnList(tmX, tmY, page, numOfRow, "json", Constant.API_KEY);
        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<MsrstnInfoInqireSvc>() {
            @Override
            public void onResponse(Call<MsrstnInfoInqireSvc> call, Response<MsrstnInfoInqireSvc> response) {
                int size = response.body().getList().size();
                for (int i = 0; i < size; i++) {
                    Log.e(TAG, "주소 = " + response.body().getList().get(i).getAddr()); //주소
                    Log.e(TAG, "관측소이름 = " + response.body().getList().get(i).getStationName()); //관측소 이름
                    Log.e(TAG, "거리 = " + response.body().getList().get(i).getTm()); //거리
                }
                Log.e(TAG, "TotalCount = " + response.body().getTotalCount()); //카운트
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<MsrstnInfoInqireSvc> call, Throwable t) {
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + t.getMessage());
            }
        });
    }


    /**
     * * 측정소 목록 조회
     *
     * @param addr
     * @param stationName
     * @param page
     * @param numOfRow
     */
    public void getMsrstnList(String addr, String stationName, int page, int numOfRow) {
        Call<MsrstnInfoInqireSvc> call = airapiinfo.getMsrstnList(addr, stationName, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<MsrstnInfoInqireSvc>() {
            @Override
            public void onResponse(Call<MsrstnInfoInqireSvc> call, Response<MsrstnInfoInqireSvc> response) {
                int size = response.body().getList().size();
                for (int i = 0; i < size; i++) {
                    Log.e(TAG, "주소 = " + response.body().getList().get(i).getAddr()); //주소
                    Log.e(TAG, "관측소이름 = " + response.body().getList().get(i).getStationName()); //관측소 이름
                }
                Log.e(TAG, "TotalCount = " + response.body().getTotalCount()); //카운트
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<MsrstnInfoInqireSvc> call, Throwable t) {
                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 동이름으로 TM 기준좌표조회
     *
     * @param umdName
     */
    public void getTMStdrCrdnt(String umdName) {
        Call<MsrstnInfoInqireSvc> call = airapiinfo.getTMStdrCrdnt(umdName, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<MsrstnInfoInqireSvc>() {
            @Override
            public void onResponse(Call<MsrstnInfoInqireSvc> call, Response<MsrstnInfoInqireSvc> response) {
                Log.e(TAG, "tmX = " + response.body().getList().get(0).getTmX());
                Log.e(TAG, "tmY = " + response.body().getList().get(0).getTmY());
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<MsrstnInfoInqireSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 측정소별 실시간 측정정보 조회
     *
     * @param stationName
     * @param dateTerm
     * @param page
     * @param numOfRow
     */
    public void getMsrstnAcctoRltmMesureDnsty(String stationName, String dateTerm, String ver, int page, int numOfRow) {
        Call<ArpltnInforInqireSvc> call = airapiinfo.getMsrstnAcctoRltmMesureDnsty(stationName, dateTerm, ver, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnInforInqireSvc>() {
            @Override
            public void onResponse(Call<ArpltnInforInqireSvc> call, Response<ArpltnInforInqireSvc> response) {
                int size = response.body().getList().size();
                for (int i = 0; i < size; i++) {
                    Log.e(TAG, "시간 = " + response.body().getList().get(i).getDataTime());
                    Log.e(TAG, "No2Grade = " + response.body().getList().get(i).getNo2Grade());
                    Log.e(TAG, "O3Grade = " + response.body().getList().get(i).getO3Grade());
                    Log.e(TAG, "Pm10Grade = " + response.body().getList().get(i).getPm10Grade());
                    Log.e(TAG, "Pm25Grade = " + response.body().getList().get(i).getPm25Grade());
                    Log.e(TAG, "Pm25Grade = " + response.body().getList().get(i).getSo2Grade());

                }
                Log.e(TAG, "TotalCount = " + response.body().getTotalCount()); //카운트
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnInforInqireSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }


    /**
     * 통합대기환경 지수 나쁨이상 측정소 목록조회 오퍼레이션 명세
     *
     * @param page
     * @param numOfRow
     */
    public void getUnityAirEnvrnIdexSnstiveAboveMsrstnList(int page, int numOfRow) {
        Call<ArpltnInforInqireSvc> call = airapiinfo.getUnityAirEnvrnIdexSnstiveAboveMsrstnList(page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnInforInqireSvc>() {
            @Override
            public void onResponse(Call<ArpltnInforInqireSvc> call, Response<ArpltnInforInqireSvc> response) {
                int size = response.body().getList().size();
                for (int i = 0; i < size; i++) {
//                    Log.e(TAG, "시간 = " + response.body().getList().get(i).getDataTime());
//                    Log.e(TAG, "No2Grade = " + response.body().getList().get(i).getNo2Grade());
//                    Log.e(TAG, "O3Grade = " + response.body().getList().get(i).getO3Grade());
//                    Log.e(TAG, "Pm10Grade = " + response.body().getList().get(i).getPm10Grade());
//                    Log.e(TAG, "Pm25Grade = " + response.body().getList().get(i).getPm25Grade());
//                    Log.e(TAG, "Pm25Grade = " + response.body().getList().get(i).getSo2Grade());

                }
                Log.e(TAG, "TotalCount = " + response.body().getTotalCount()); //카운트
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnInforInqireSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 통합대기환경 지수 나쁨이상 측정소 목록조회 오퍼레이션 명세
     * 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종
     *
     * @param sidoName
     * @param ver
     * @param page
     * @param numOfRow
     */
    public void getCtprvnRltmMesureDnsty(String sidoName, String ver, int page, int numOfRow) {
        Call<ArpltnInforInqireSvc> call = airapiinfo.getCtprvnRltmMesureDnsty(sidoName, ver, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnInforInqireSvc>() {
            @Override
            public void onResponse(Call<ArpltnInforInqireSvc> call, Response<ArpltnInforInqireSvc> response) {
                int size = response.body().getList().size();
                for (int i = 0; i < size; i++) {
                    Log.e(TAG, "시간 = " + response.body().getList().get(i).getDataTime());
                    Log.e(TAG, "도시 = " + response.body().getList().get(i).getStationName());
                    Log.e(TAG, "No2Grade = " + response.body().getList().get(i).getNo2Grade());
                    Log.e(TAG, "O3Grade = " + response.body().getList().get(i).getO3Grade());
                    Log.e(TAG, "Pm10Grade = " + response.body().getList().get(i).getPm10Grade());
                    Log.e(TAG, "Pm25Grade = " + response.body().getList().get(i).getPm25Grade());
                    Log.e(TAG, "So2Grade = " + response.body().getList().get(i).getSo2Grade());
                }
                Log.e(TAG, "TotalCount = " + response.body().getTotalCount()); //카운트
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnInforInqireSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 미세먼지/오존 예보통보조회
     *
     * @param informCode (PM10 : 미세먼지 PM25 : 초미세먼지 O3 : 오존)
     * @param searchDate
     * @param ver
     * @param page
     * @param numOfRow
     */
    public void getMinuDustFrcstDspth(String informCode, String searchDate, String ver, int page, int numOfRow) {
        Call<MinuDustFrcstDspth> call = airapiinfo.getMinuDustFrcstDspth(informCode, searchDate, ver, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<MinuDustFrcstDspth>() {
            @Override
            public void onResponse(Call<MinuDustFrcstDspth> call, Response<MinuDustFrcstDspth> response) {
                int size = response.body().getList().size();
                for (int i = 0; i < size; i++) {
                    Log.e(TAG, "dateTiem = " + response.body().getList().get(i).getInformData()); //예보날짜
                    Log.e(TAG, "InformCause = " + response.body().getList().get(i).getInformCause()); //예보
                    ; //예보
                }
                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<MinuDustFrcstDspth> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }


    /**
     * 시도별 실시간 평균정보 조회 오퍼레이션 명세
     *
     * @param informCode
     * @param dataGubun
     * @param searchCondition
     * @param page
     * @param numOfRow
     */
    public void getCtprvnMesureLIst(String informCode, String dataGubun, String searchCondition, int page, int numOfRow) {
        Call<CtprvnMesureList> call = airapiinfo.getCtprvnMesureLIst(informCode, dataGubun, searchCondition, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<CtprvnMesureList>() {
            @Override
            public void onResponse(Call<CtprvnMesureList> call, Response<CtprvnMesureList> response) {

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<CtprvnMesureList> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 시군구별 실시간 평균정보 조회
     *
     * @param sidoName
     * @param searchCondition
     * @param page
     * @param numOfRow
     */
    public void getCtprvnMesureSidoLIst(String sidoName, String searchCondition, int page, int numOfRow) {
        Call<CtprvnMesureListSido> call = airapiinfo.getCtprvnMesureSidoLIst(sidoName, searchCondition, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<CtprvnMesureListSido>() {
            @Override
            public void onResponse(Call<CtprvnMesureListSido> call, Response<CtprvnMesureListSido> response) {

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<CtprvnMesureListSido> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 측정소별 최종 확정 농도 조회
     *
     * @param stationName
     * @param searchCondition
     * @param page
     * @param numOfRow
     */
    public void getMsrstnAcctoLastDcsnDnsty(String stationName, String searchCondition, int page, int numOfRow) {
        Call<ArpltnStatsSvc> call = airapiinfo.getMsrstnAcctoLastDcsnDnsty(stationName, searchCondition, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnStatsSvc>() {
            @Override
            public void onResponse(Call<ArpltnStatsSvc> call, Response<ArpltnStatsSvc> response) {

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnStatsSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }


    /**
     * 기간별 오염통계 정보 조회
     *
     * @param searchDataTime
     * @param statArticleCondition
     * @param page
     * @param numOfRow
     */
    public void getDatePollutnStatInfo(String searchDataTime, String statArticleCondition, int page, int numOfRow) {
        Call<ArpltnStatsSvc> call = airapiinfo.getDatePollutnStatInfo(searchDataTime, statArticleCondition, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnStatsSvc>() {
            @Override
            public void onResponse(Call<ArpltnStatsSvc> call, Response<ArpltnStatsSvc> response) {

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnStatsSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 오존주의보 발생 정보 조회
     * @param year
     * @param page
     * @param numOfRow
     */
    public void getOzAdvsryOccrrncInfo(String year, int page, int numOfRow) {
        Call<ArpltnStatsSvc> call = airapiinfo.getOzAdvsryOccrrncInfo(year, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnStatsSvc>() {
            @Override
            public void onResponse(Call<ArpltnStatsSvc> call, Response<ArpltnStatsSvc> response) {

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnStatsSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }

    /**
     * 황사주의보 발생 정보 조회
     * @param year
     * @param page
     * @param numOfRow
     */
    public void getYlwsndAdvsryOccrrncInfo(String year, int page, int numOfRow) {
        Call<ArpltnStatsSvc> call = airapiinfo.getOzAdvsryOccrrncInfo(year, page, numOfRow, "json", Constant.API_KEY);

        Log.e(TAG, "request = " + call.request().toString());
        call.enqueue(new Callback<ArpltnStatsSvc>() {
            @Override
            public void onResponse(Call<ArpltnStatsSvc> call, Response<ArpltnStatsSvc> response) {

                Log.i(TAG, "성공" + response.code());
            }

            @Override
            public void onFailure(Call<ArpltnStatsSvc> call, Throwable t) {
//                Toast.makeText(mCtx, "와이파이 연결 후, 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "실패");
                Log.e(TAG, "Throwable = " + Throwable.class.toString());
            }
        });
    }
}
