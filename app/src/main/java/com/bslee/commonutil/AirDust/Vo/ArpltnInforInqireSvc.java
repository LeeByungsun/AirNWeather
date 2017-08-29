package com.bslee.commonutil.AirDust.Vo;

import java.util.List;

/**
 * Created by bslee on 2017-04-26.
 */

/**
 * ArpltnInforInqireSvc json type
 */
public class ArpltnInforInqireSvc {
    List<ArpltnInforInqireSvcItem> list;
    ArpltnInforInqireSvcItem parm;
    ArpltnInforInqireSvcItem ArpltnInforInqireSvcVo;
    int totalCount;

    public List<ArpltnInforInqireSvcItem> getList() {
        return list;
    }

    public ArpltnInforInqireSvcItem getParm() {
        return parm;
    }

    public ArpltnInforInqireSvcItem getArpltnInforInqireSvcVo() {
        return ArpltnInforInqireSvcVo;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
