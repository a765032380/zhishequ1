package com.bjxiyang.zhinengshequ.myapplication.until;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.WindowManager;
import android.widget.EditText;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by sander on 2017/4/11.
 */

public class AppUnit {

    public static String getSha1(String str){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }


//    public static HashMap<String,ContractInfo> getPhoneContracts(Context mContext){
//        HashMap<String, ContractInfo> map = new HashMap<String, ContractInfo>();
//        try {
//            ContentResolver resolver = mContext.getContentResolver();
//            // 获取手机联系人
//            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null, null); //传入正确的uri
//            if(phoneCursor!=null){
//                while(phoneCursor.moveToNext()){
//                    int nameIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME); //获取联系人name
//                    String name = phoneCursor.getString(nameIndex);
//                    String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); //获取联系人number
//                    if(TextUtils.isEmpty(phoneNumber)){
//                        continue;
//                    }
//                    //以下是我自己的数据封装。
//                    ContractInfo contractInfo = new ContractInfo();
//                    contractInfo.setUname(name);
//                    contractInfo.setPhone(phoneNumber);
//                    map.put(phoneNumber, contractInfo);
//
//                    Log.d("sander",phoneNumber + " --- " + name);
//                }
//                phoneCursor.close();
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return map;
//    }
//    public static HashMap<String , ContractInfo> getSimContracts(Context mContext){
//        //读取SIM卡手机号,有两种可能:content://icc/adn与content://sim/adn
//        HashMap<String, ContractInfo> map = new HashMap<String, ContractInfo>();
//
//     ContentResolver resolver = mContext.getContentResolver();
//        Uri uri = Uri.parse("content://icc/adn");
//        Cursor phoneCursor = resolver.query(uri,null, null, null, null);
//        if(phoneCursor!=null){
//            while(phoneCursor.moveToNext()){
//                String name = phoneCursor.getString(phoneCursor.getColumnIndex("name"));
//                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex("number"));
//                if(TextUtils.isEmpty(phoneNumber)){
//                     continue;
//                     }
//                 //以下是我自己的数据封装。
//                ContractInfo contractInfo = new ContractInfo();
//                contractInfo.setUname(name);
//                contractInfo.setPhone(phoneNumber);
//                map.put(phoneNumber, contractInfo);
//                Log.d("sander",phoneNumber + " --- " + name);
//
//            }
//            phoneCursor.close();
//        }
//        return map;
//    }

    public static void copytext(String text,Context context)
    {
        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager)context.getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
    }


    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }





}
