package com.bjxiyang.zhinengshequ.myapplication.greendao;

import android.content.Context;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class DaoUtils {

    private  static GouWuCheManager studentManager;
    public  static Context context;

    public static void init(Context context){
        DaoUtils.context = context;
    }

    /**
     * 单列模式获取StudentManager对象
     * @return
     */
    public static GouWuCheManager getStudentInstance(){
        if (studentManager == null) {
            studentManager = new GouWuCheManager(context);
        }
        return studentManager;
    }
}
