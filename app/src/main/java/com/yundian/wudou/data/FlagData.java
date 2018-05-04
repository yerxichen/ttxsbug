package com.yundian.wudou.data;

/**
 * Created by cookie on 2016/8/15.
 */
public class FlagData {
    public static final int DELAY_BANNER = 3000;

    //sharedpreferences title
    public final static String FLAG_SHAREDPREFERENCES = "sharedpreferencesmanager_wudou";
    public final static String FLAG_FIRSTSHOW = "sharedpreferencesmanager_firstshow";
    public final static String FLAG_TOKEN = "sharedpreferencesmanager_token";
    public final static String FLAG_ISLOGIN = "sharedpreferencesmanager_islogin";
    public final static String FLAG_EVALUATEJSON = "sharedpreferencesmanager_evaluatejson";
    public final static String FLAG_HOMEPAGEJSON = "sharedpreferencesmanager_homepagejson";


    //网络请求地址
    public final static String FLAG_IMGADDRESS = "http://admin.wudoll.com/njsqupfiles/";
    public final static String FLAG_WEB_ABOUTUS = "http://admin.wudoll.com/wapapp/about.html?access_token=";
    //public final static String FLAG_WEB_CONSUMERCOMMENT = "http://admin.wudoll.com/wapapp/productreviewsall.html?";
    public final static String FLAG_WEB_COUPONRULES = "http://admin.wudoll.com/wapapp/coupons.html?access_token=";
    public final static String FLAG_WEB_INTERGALSHOPCOMMODITYDETAILS = "http://admin.wudoll.com/wapapp/creditsinfo.html?";
    public final static String FLAG_WEB_MORECOMMODITYDETAILS = "http://admin.wudoll.com/wapapp/productsinfo.html?";
    public final static String FLAG_WEB_REGISTAGREEMENTACTIVITY = "http://admin.wudoll.com/wapapp/agreement.html?access_token=";

    public final static String FLAG_WEB_CONVENIENCESERVICESDETAILS = "http://admin.wudoll.com/wapapp/myserviceinfo.html?";
    public final static String FLAG_WEB_MYCONVENIENCESERVICESDETAILS = "http://admin.wudoll.com/wapapp/myserviceinfo.html?";
    public final static String FLAG_WEB_SECONDHANDCOMMODITYDETAILS = "http://admin.wudoll.com/wapapp/secondhand.html?";
    public final static String FLAG_WEB_MYSECONDHANDCOMMODITYDETAILS = "http://admin.wudoll.com/wapapp/secondhand.html?";

    //public final static String FLAG_WEB_MYCOMMENT = "http://admin.wudoll.com/wapapp/myreviews.html?access_token=";
    public final static String FLAG_WEB_HELP = "http://admin.wudoll.com/wapapp/help.html?access_token=";
    public final static String FLAG_WEB_MERCHANTMESSAGE = "http://admin.wudoll.com/chatlogapp/storechatlogusers.html?";
    public final static String FLAG_WEB_MESSAGECENTER = "http://admin.wudoll.com/wapapp/message.html?access_token=";
    public final static String FLAG_WEB_HEADLINE = "http://admin.wudoll.com/wapapp/hotnews.html?access_token=";

    //网络请求中api中的type,device,status等
    public final static String FLAG_NETWORK_DEVICE = "2";
    public final static String FLAG_NETWORK_MSGTYPE_REGISTER = "1";
    public final static String FLAG_NETWORK_MSGTYPE_MODIFY = "2";
    public final static String FLAG_NETWORK_ADD_ONE = "1";
    public final static String FLAG_NETWORK_ADD_TWO = "2";
    public final static String FLAG_NETWORK_ADD_THREE = "3";
    public final static String FLAG_NETWORK_ADD_FOUR = "4";

    //Intent跳转过程中传值Flag
    public final static String FLAG_SEARCH_TEXT = "flag_search_text";
    public final static String FLAG_SEARCH_CATENUMBER = "flag_search_catenumber";

    public final static String FLAG_SHOP_ID = "flag_shop_id";
    public final static String FLAG_PRODUCT_ID = "flag_product_id";
    public final static String FLAG_NEWS_ID = "flag_news_id";
    public final static String FLAG_REGION = "flag_region";

    public final static String FLAG_STORE_CODE = "flag_store_code";
    public final static String FLAG_OID = "flag_oid";
    public final static String FLAG_OSN = "flag_osn";
    public final static String FLAG_TITLE = "flag_title";
    public final static String FLAG_STATE = "flag_state";
    public final static String FLAG_MONEY = "flag_money";
    public final static String FLAG_PAYSTATE = "flag_paystate";
    public final static String FLAG_RELEASE = "flag_release";
    public final static String FLAG_SNTYPE = "flag_sntype";
    public final static String FLAG_SN = "flag_sn";
    public final static String FLAG_TYPE = "flag_type";
    public final static String FLAG_MESSAGE_SID = "flag_message_sid";
    public final static String FLAG_MESSAGE_PID = "flag_message_pid";
}
