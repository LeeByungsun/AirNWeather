package com.bslee.commonutil.AirDust;

import com.bslee.commonutil.AirDust.Vo.ArpltnInforInqireSvc;
import com.bslee.commonutil.AirDust.Vo.ArpltnStatsSvc;
import com.bslee.commonutil.AirDust.Vo.CtprvnMesureList;
import com.bslee.commonutil.AirDust.Vo.CtprvnMesureListSido;
import com.bslee.commonutil.AirDust.Vo.MinuDustFrcstDspth;
import com.bslee.commonutil.AirDust.Vo.MsrstnInfoInqireSvc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by bslee on 2017-04-18.
 */

public interface AirAPIInfo {

    //server Url
    public static final String ObserverAPI = "http://openapi.airkorea.or.kr/openapi/services/rest/";

    /**
     * 근접 측정소 목록 조회
     *
     * @param tmX        tm X좌표
     * @param tmY        tm Y좌표
     * @param pageno     페이지번호
     * @param mumOfRows  한페이지 결과수
     * @param returntype 응답요청 타입(json설정)
     * @param apikkey    server key
     * @return
     */
    @FormUrlEncoded
    @POST("MsrstnInfoInqireSvc/getNearbyMsrstnList")
    Call<MsrstnInfoInqireSvc> getNearbyMsrstnList(@Field("tmX") double tmX, @Field("tmY") double tmY, @Field("pageNo") int pageno, @Field("numOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 측정소 목록 조회
     *
     * @param addr        측정소주소
     * @param stationName 측정소이름
     * @param pageno      페이지번호
     * @param mumOfRows   한페이지 결과수
     * @param returntype  응답요청 타입(json설정)
     * @param apikkey     server key
     * @return
     */
    @FormUrlEncoded
    @POST("MsrstnInfoInqireSvc/getMsrstnList")
    Call<MsrstnInfoInqireSvc> getMsrstnList(@Field("addr") String addr, @Field("stationName") String stationName, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 동이름으로 TM 기준좌표조회
     *
     * @param umdName    측정소 이름
     * @param returntype 응답요청 타입(json설정)
     * @param apikkey    server key
     * @return
     */
    @FormUrlEncoded
    @POST("MsrstnInfoInqireSvc/getTMStdrCrdnt")
    Call<MsrstnInfoInqireSvc> getTMStdrCrdnt(@Field("umdName") String umdName, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 측정소별 실시간 측정정보 조회
     *
     * @param stationName 측정소이름
     * @param dataTerm    데이터기간(DAILY, MONTH, 3MONTH)
     * @param ver         오퍼레이션 버전(1.3)
     * @param pageno      페이지번호
     * @param mumOfRows   한페이지 결과수
     * @param returntype  응답요청 타입(json설정)
     * @param apikkey     server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty")
    Call<ArpltnInforInqireSvc> getMsrstnAcctoRltmMesureDnsty(@Field("stationName") String stationName, @Field("dataTerm") String dataTerm, @Field("ver") String ver, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 통합대기환경 지수 나쁨이상 측정소 목록조회 오퍼레이션 명세
     *
     * @param pageno     페이지번호
     * @param mumOfRows  한페이지 결과수
     * @param returntype 응답요청 타입(json설정)
     * @param apikkey    server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnInforInqireSvc/getUnityAirEnvrnIdexSnstiveAboveMsrstnList")
    Call<ArpltnInforInqireSvc> getUnityAirEnvrnIdexSnstiveAboveMsrstnList(@Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 시도별 실시간 측정정보 조회 오퍼레이션 명세
     *
     * @param sidoName   시도이름
     * @param ver        오퍼레이션 버전(1.3)
     * @param pageno     페이지번호
     * @param mumOfRows  한페이지 결과수
     * @param returntype 응답요청 타입(json설정)
     * @param apikkey    server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty")
    Call<ArpltnInforInqireSvc> getCtprvnRltmMesureDnsty(@Field("sidoName") String sidoName, @Field("ver") String ver, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);


    /**
     * 미세먼지/오존 예보통보조회
     *
     * @param InformCode
     * @param searchDate
     * @param pageno
     * @param mumOfRows
     * @param returntype
     * @param apikkey
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnInforInqireSvc/getMinuDustFrcstDspth")
    Call<MinuDustFrcstDspth> getMinuDustFrcstDspth(@Field("informCode") String InformCode, @Field("searchDate") String searchDate, @Field("ver") String ver, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 시도별 실시간 평균정보 조회 오퍼레이션 명세
     *
     * @param itemCode        항목명(SO2, CO, O3, NO2, PM10, PM25)
     * @param dataGubun       자료 구분(HOUR, DAILY)
     * @param searchCondition 데이터기간(WEEK,MONTH)
     * @param pageno          페이지번호
     * @param mumOfRows       한페이지 결과수
     * @param returntype      응답요청 타입(json설정)
     * @param apikkey         server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnInforInqireSvc/getCtprvnMesureLIst")
    Call<CtprvnMesureList> getCtprvnMesureLIst(@Field("itemCode") String itemCode, @Field("dataGubun") String dataGubun, @Field("searchCondition") String searchCondition, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 시군구별 실시간 평균정보 조회
     *
     * @param sidoName        시도명
     * @param searchCondition 데이터기간
     * @param pageno          페이지 번호
     * @param mumOfRows       한페이지 결과수
     * @param returntype      응답요청 타입(json설정)
     * @param apikkey         server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnInforInqireSvc/getCtprvnMesureSidoLIst")
    Call<CtprvnMesureListSido> getCtprvnMesureSidoLIst(@Field("sidoName") String sidoName, @Field("searchCondition") String searchCondition, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);


    /**
     * 측정소별 최종 확정 농도 조회
     *
     * @param stationName     측정소 명
     * @param searchCondition 년, 월, 일별(YEAR, MONTH, DAILY)
     * @param pageno          페이지 번호
     * @param mumOfRows       한페이지 결과수
     * @param returntype      응답요청 타입(json설정)
     * @param apikkey         server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnStatsSvc/getMsrstnAcctoLastDcsnDnsty")
    Call<ArpltnStatsSvc> getMsrstnAcctoLastDcsnDnsty(@Field("stationName") String stationName, @Field("searchCondition") String searchCondition, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 기간별 오염통계 정보 조회
     *
     * @param searchDataTime       조회날짜
     * @param statArticleCondition 측정망정보(도시대기, 도로변대기, 국가배경농도, 교외대기)
     * @param pageno               페이지 번호
     * @param mumOfRows            한페이지 결과수
     * @param returntype           응답요청 타입(json설정)
     * @param apikkey              server key
     * @return
     */
    @FormUrlEncoded
    @POST("ArpltnStatsSvc/getDatePollutnStatInfo")
    Call<ArpltnStatsSvc> getDatePollutnStatInfo(@Field("searchDataTime") String searchDataTime, @Field("statArticleCondition") String statArticleCondition, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 오존주의보 발생 정보 조회
     *
     * @param year
     * @param pageno
     * @param mumOfRows
     * @param returntype
     * @param apikkey
     * @return
     */
    @FormUrlEncoded
    @POST("OzYlwsndOccrrncInforInqireSvc/getOzAdvsryOccrrncInfo")
    Call<ArpltnStatsSvc> getOzAdvsryOccrrncInfo(@Field("year") String year, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

    /**
     * 황사주의보 발생 정보 조회
     *
     * @param year
     * @param pageno
     * @param mumOfRows
     * @param returntype
     * @param apikkey
     * @return
     */
    @FormUrlEncoded
    @POST("OzYlwsndOccrrncInforInqireSvc/getYlwsndAdvsryOccrrncInfo")
    Call<ArpltnStatsSvc> getYlwsndAdvsryOccrrncInfo(@Field("year") String year, @Field("pageNo") int pageno, @Field("mumOfRows") int mumOfRows, @Field("_returnType") String returntype, @Field(value = "ServiceKey", encoded = true) String apikkey);

}

