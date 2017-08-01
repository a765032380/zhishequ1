package com.bjxiyang.zhinengshequ.myapplication.greendao;

import android.content.Context;

import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuChe;
import com.bjxiyang.zhinengshequ.myapplication.bianlidian.GouWuCheDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class GouWuCheManager extends BaseDao {
    public GouWuCheManager(Context context) {
        super(context);
    }

    /***************************数据库查询*************************/

    /**
     * 通过ID查询对象
     * @param id
     * @return
     */
    private GouWuChe loadById(long id){
        return daoSession.getGouWuCheDao().load(id);
    }

    /**
     * 获取某个对象的主键ID
     * @param student
     * @return
     */
    private long getID(GouWuChe student){

        return daoSession.getGouWuCheDao().getKey(student);
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<GouWuChe> getStudentByName(String key){
        QueryBuilder queryBuilder =  daoSession.getGouWuCheDao().queryBuilder();
        queryBuilder.where(GouWuCheDao.Properties.Name.eq(key));
        int size = queryBuilder.list().size();
        if (size > 0){
            return queryBuilder.list();
        }else{
            return null;
        }
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<Long> getIdByName(String key){
        List<GouWuChe> students = getStudentByName(key);
        List<Long> ids = new ArrayList<Long>();
        int size = students.size();
        if (size > 0){
            for (int i = 0;i < size;i++){
                ids.add(students.get(i).getId());
            }
            return ids;
        }else{
            return null;
        }
    }

    /***************************数据库删除*************************/

    /**
     * 根据ID进行数据库的删除操作
     * @param id
     */
    private void deleteById(long id){

        daoSession.getGouWuCheDao().deleteByKey(id);
    }


    /**
     * 根据ID同步删除数据库操作
     * @param ids
     */
    private void deleteByIds(List<Long> ids){

        daoSession.getGouWuCheDao().deleteByKeyInTx(ids);
    }



    /***********************************
     * 在次添加一些Student特有的数据库操作语句
     * ************************************/

}
