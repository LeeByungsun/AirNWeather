package com.bslee.commonutil.AirDust.Vo;

/**
 * Created by bslee on 2017-04-20.
 */

import java.util.List;

/**
 * MinuDustFrcstDspth json type
 */
public class MinuDustFrcstDspth {

    MinuDustFrcstDspthItem MinuDustFrcstDspthVo;
    List<MinuDustFrcstDspthItem> list;
    MinuDustFrcstDspthItem parm;
    int totalCount;

    public MinuDustFrcstDspthItem getMinuDustFrcstDspthVo() {
        return MinuDustFrcstDspthVo;
    }

    public List<MinuDustFrcstDspthItem> getList() {
        return list;
    }

    public MinuDustFrcstDspthItem getParm() {
        return parm;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
