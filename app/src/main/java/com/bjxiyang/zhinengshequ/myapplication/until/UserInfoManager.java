package com.bjxiyang.zhinengshequ.myapplication.until;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sander on 2017/4/8.
 */

public class UserInfoManager {
    private static final String USER_INFO_SAVEKEY = "GUARDUSER";
    private static  final String USER_INFO_ACCOUNT = "ACCOUNT";
    private static final String USER_INFO_PASSWORLD = "PASSWORLD";
    private static final String USER_INFO_ID = "USERID";
    private static final String FIRST_RUN_APP = "firstrunapp";

    private static final String Fuck_User_phone_save = "userphoneave";
    private static final String fuck_user_password_save = "userpasswordsave";

    private static UserInfoManager manager = null;
    public String account;

    public String user_id;


    public String fuck_passworld;
    public String fuck_phone;


//    public ResponseUserLoginData getUserInfo() {
//        return userInfo;
//    }

//    public void setUserInfo(ResponseUserLoginData userInfo) {
//        this.userInfo = userInfo;
//    }

//    public ResponseUserLoginData userInfo;

    public int screen_width;
    public int screen_height;

    private UserInfoManager(){
        //数据的初始化在这里做了

    }

    public static UserInfoManager sharedManager(){
        // 提供一个全局的静态方法
        if (manager == null){
            synchronized (UserInfoManager.class){
                if (manager == null){
                    manager = new UserInfoManager();
                }
            }
        }
        return manager;
    }

    public void saveUserInfo(String account,String user_id,Context context){
        this.account = account;
        this.user_id = user_id;
        SharedPreferences sp =context.getSharedPreferences(USER_INFO_SAVEKEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER_INFO_ACCOUNT,account);
        editor.putString(USER_INFO_ID,user_id);
        editor.commit();
    }

    public void saveFuckUserInfo(String phone, String passworld, Context context){
        this.fuck_passworld = passworld;
        this.fuck_phone = phone;
        SharedPreferences sp =context.getSharedPreferences(USER_INFO_SAVEKEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Fuck_User_phone_save,phone);
        editor.putString(fuck_user_password_save,passworld);
        editor.commit();
    }

    public void checkoutFuckSaveuserInfo(Context context){
        SharedPreferences sp =context.getSharedPreferences(USER_INFO_SAVEKEY, MODE_PRIVATE);
        this.fuck_phone = sp.getString(Fuck_User_phone_save,null);
        this.fuck_passworld = sp.getString(fuck_user_password_save,null);
    }





    public void getUserInfoFromPreference(Context context){
        SharedPreferences sp =context.getSharedPreferences(USER_INFO_SAVEKEY, MODE_PRIVATE);
        this.account = sp.getString(USER_INFO_ACCOUNT,null);
        this.user_id = sp.getString(USER_INFO_ID,null);
    }
    public boolean checkoutUserisLogin(Context context){
        this.getUserInfoFromPreference(context);
        if (this.user_id == null){
            return false;
        }
        return true;
    }

    public void fristRunApp(Context context)
    {
        SharedPreferences sp =context.getSharedPreferences(USER_INFO_SAVEKEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(FIRST_RUN_APP,true);
        editor.commit();
    }
    public boolean isFirstRunApp(Context context)
    {
        SharedPreferences sp =context.getSharedPreferences(USER_INFO_SAVEKEY, MODE_PRIVATE);
        return sp.getBoolean(FIRST_RUN_APP,false);
    }


}
