package com.bjxiyang.zhinengshequ.myapplication.zhifubao;

import android.text.TextUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/30 0030.
 */

public class AuthResult {

    private String resultStatus;//结果状态
    private String result;//结果
    private String memo;//备忘录
    private String resultCode;//结果码
    private String authCode;//确认码
    private String alipayOpenId;//支付宝开放ID
    /**
     * 确认结果，参数为键值对全是string型的’结果行‘和一个boolean类型的参数'去除所有括号了吗'
     * @param rawResult
     * @param removeBrackets
     */

    public AuthResult(Map<String, String> rawResult, boolean removeBrackets) {
        if (rawResult == null) {//传入的结果行map不为空才往下进行
            return;
        }

        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {//获取传入map中的键值对为resultStatus的值放入到resultStatus中（结果状态信息）
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {//获取传入map中的键值对的结果
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {////获取传入map中的备注（备忘录）
                memo = rawResult.get(key);
            }
        }

        String[] resultValue = result.split("&");//将结果用&符号拆分
        //对传入数组进行遍历
        for (String value : resultValue) {
            if (value.startsWith("alipay_open_id")) {//如果以alipay_open_id(支付宝开放id)开始的
                //此时的value是以alipay_open_id开头的值；支付宝的开放id=截取到的alipay_open_id字串去除括号
                alipayOpenId = removeBrackets(getValue("alipay_open_id=", value), removeBrackets);
                continue;
            }
            if (value.startsWith("auth_code")) {//如果循环进行到确认码"auth_code"
                authCode = removeBrackets(getValue("auth_code=", value), removeBrackets);//处理得到确认码
                continue;
            }
            if (value.startsWith("result_code")) {//如果解析到结果码
                resultCode = removeBrackets(getValue("result_code=", value), removeBrackets);//处理得到结果码
                continue;
            }
        }

    }
    /**
     * 移除括号的方法
     * @param str 传入的字符串
     * @param remove 移除吗？
     * @return 返回处理过后的字符串
     */
    private String removeBrackets(String str, boolean remove) {
        if (remove) {//如果是移除的话
            if (!TextUtils.isEmpty(str)) {//如果传入的字符串不是空的话，则将对字串进行一下操作
                if (str.startsWith("\"")) {
                    str = str.replaceFirst("\"", "");//移除第一个斜杠
                }
                if (str.endsWith("\"")) {
                    str = str.substring(0, str.length() - 1);//移除最后的斜杠
                }
            }
        }
        return str;//返回处理过后的字符串
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo + "};result={" + result + "}";
    }
    /**
     * 获取值得方法
     * @param header 头部字符串
     * @param data 数据字符串
     * @return
     */
    private String getValue(String header, String data) {
        return data.substring(header.length(), data.length());//将data从截取头部的长度处截取到传入data的末尾
    }

    /**
     * 获取String类型的结果状态
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * 获取备注
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 获取结果
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * 获取结果码
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 获取确认码
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * 获取支付宝开放支付ID
     * @return the alipayOpenId
     */
    public String getAlipayOpenId() {
        return alipayOpenId;
    }
}