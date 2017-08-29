package com.bslee.commonutil.AirDust.Vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bslee on 2017-04-20.
 */

/**
 * MsrstnInfoInqireSvc json type
 */
public class MsrstnInfoInqireSvc {
    @SerializedName("MsrstnInfoInqireSvrVo")
    MsrstnInfoInqireSvcItem MsrstnInfoInqireSvrVo;
    @SerializedName("list")
    List<MsrstnInfoInqireSvcItem> list;
    @SerializedName("parm")
    MsrstnInfoInqireSvcItem parm;
    @SerializedName("totalCount")
    int totalCount;

    public MsrstnInfoInqireSvcItem getMsrstnInfoInqireSvrVo() {
        return MsrstnInfoInqireSvrVo;
    }

    public List<MsrstnInfoInqireSvcItem> getList() {
        return list;
    }

    public MsrstnInfoInqireSvcItem getParm() {
        return parm;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
