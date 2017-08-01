package com.bjxiyang.zhinengshequ.myapplication.until;

import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.greendao.DaoUtils;
import com.bjxiyang.zhinengshequ.myapplication.manager.SPManager;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class UnitGetGouWuChe {
    private static double zongji;
    private static int count;
    private static List<GouWuChe> mList;

    public static double getZongJia() {
        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        zongji = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getSellerId() == SPManager.getInstance().getInt("sellerId", 0)) {
                if (mList.get(i).getIfDiscount() == 0) {
                    zongji = zongji + mList.get(i).getCount() * mList.get(i).getPrice();
                } else {
                    zongji = zongji + mList.get(i).getCount() * mList.get(i).getDiscountPrice();
                }
            }

        }
        return zongji / 100;
    }

    public static int getConuntAll() {
        mList = DaoUtils.getStudentInstance().QueryAll(GouWuChe.class);
        count = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getSellerId() == SPManager.getInstance().getInt("sellerId", 0)) {
                count = count + mList.get(i).getCount();
            }
        }
        return count;
    }


}
