package com.bjxiyang.zhinengshequ.myapplication.model;

import java.util.List;

/**
 * Created by gll on 17-5-23.
 */

public class XY_City {


    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private List<String> xiaoqu;
    private List<String> louhao;
    private List<String> danyuan;

    public List<String> getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(List<String> xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public List<String> getLouhao() {
        return louhao;
    }

    public void setLouhao(List<String> louhao) {
        this.louhao = louhao;
    }

    public List<String> getDanyuan() {
        return danyuan;
    }

    public void setDanyuan(List<String> danyuan) {
        this.danyuan = danyuan;
    }
}
