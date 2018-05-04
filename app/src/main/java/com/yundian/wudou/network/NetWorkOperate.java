package com.yundian.wudou.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;


import com.yundian.wudou.data.HomaLoadproductsData;
import com.yundian.wudou.data.HorizontaiListviewData;
import com.yundian.wudou.data.HorizontaiListviewListData;
import com.yundian.wudou.data.SettlemCoupons;
import com.yundian.wudou.data.StoresBaseInfoData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.publicinterface.Listener;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cookie on 2016/7/21.
 */
public class NetWorkOperate {

    private static final String APPTYPE = "2";
    private static final String VERSION = "1";

    private void Log(String msg) {
        Log.e("Tag", "network-->" + msg);
    }

    private static final String ADDRESS = "http://app.wudoll.com";

    private NetWorkOperateHandler handler = new NetWorkOperateHandler();

    private String strMessage = "数据请求失败，请检查网络！";

    private OkHttpClient mOkHttpClient;
    private Request mRequest;
    private Gson mGson;

    private Context context;
    private Listener listener;

    //账户
    private NetWorkInterface.OnGetInitialTokenListener onGetInitialTokenListener;
    private NetWorkInterface.OnGetNewTokenListener onGetNewTokenListener;
    private NetWorkInterface.OnGetLoginStateListener onGetLoginStateListener;
    private NetWorkInterface.OnGetUserRegistrCodeListener onGetUserRegisterCodeListener;
    private NetWorkInterface.OnGetUserRegisterListener onGetUserRegisterListener;
    private NetWorkInterface.OnGetForgetPasswordCodeListener onGetForgetPasswordCodeListener;
    private NetWorkInterface.OnGetForgetPasswordListener onGetForgetPasswordListener;
    private NetWorkInterface.OnGetUpdatePassWordListener onGetUpdatePassWordListener;
    private NetWorkInterface.OnGetUserInformationListener onGetUserInformationListener;
    private NetWorkInterface.OnGetRegionalDataListener onGetRegionalDataListener;
    private NetWorkInterface.OnModifyUserNameListener onModifyUserNameListener;
    private NetWorkInterface.OnModifyUserPhotoListener onModifyUserPhotoListener;
    private NetWorkInterface.OnGetCurrentVersionListener onGetCurrentVersionListener;
    //private NetWorkInterface.OnLoginOutListener onLoginOutListener;
    //private NetWorkInterface.OnGetUpdateUserSexListener onGetUpdateUserSexListener;
    //我的订单
    private NetWorkInterface.OnGetMyAllOrderDataListener onGetMyAllOrderDataListener;
    private NetWorkInterface.OnGetOrderDetailsDataListener onGetOrderDetailsDataListener;
    private NetWorkInterface.OnAddOrderListener onAddOrderListener;
    private NetWorkInterface.OnGetAliPayTextListener onGetAliPayTextListener;
    private NetWorkInterface.OnGetGoEvaluateListner onGetGoEvaluateListner;
    private NetWorkInterface.OnGetCancelOrderListener onGetCancelOrderListener;
    private NetWorkInterface.OnGetApplyRefundListener onGetApplyRefundListener;
    private NetWorkInterface.OnDeleteMyOrderlistener onDeleteMyOrderlistener;

    //商户订单
    private NetWorkInterface.OnGetShowListener onGetShowListener;
    private NetWorkInterface.OnGetMerChantOrderDataListener onGetMerChantOrderDataListener;
    private NetWorkInterface.OnGetMechantOrderDetailsDataListener onGetMechantOrderDetailsDataListener;

    private NetWorkInterface.OnGetEvaluateProductListener onGetEvaluateProductListener;
    private NetWorkInterface.OnGetMoreCommentListener onGetMoreCommentListener;
    private NetWorkInterface.OnGetMyCommentListener onGetMyCommentListener;
    private NetWorkInterface.OnSubmitEvaluationListener onSubmitEvaluationListener;
    private NetWorkInterface.OnGetDeliveryTimeListener onGetDeliveryTimeListener;
    private NetWorkInterface.OnGetCouponListener onGetCouponListener;
    private NetWorkInterface.OnGetMerchantOrderEditListener onGetMerchantOrderEditListener;
    private NetWorkInterface.OnGetRefundReasonListener onGetRefundReasonListener;


    //地址
    private NetWorkInterface.OnLoadAddressListener onLoadAddressListener;
    private NetWorkInterface.OnGetNearbyAddressListener onGetNearbyAddressListener;
    private NetWorkInterface.OnAddAddressListener onAddAddressListener;
    private NetWorkInterface.OnEditAddressListener onEditAddressListener;
    private NetWorkInterface.OnDeleteAddressListener onDeleteAddressListener;
    //数据
    private NetWorkInterface.OnGetHomaPageDataListener onGetHomaPageDataListener;
    private NetWorkInterface.OnGetHomaPageAdvertsListOneListener onGetHomaPageAdvertsListOneListener;
    private NetWorkInterface.OnGetHomaPageAdvertsListTwoListener onGetHomaPageAdvertsListTwoListener;
    private NetWorkInterface.OnGetHomaPageAdvertsListThreeListener onGetHomaPageAdvertsListThreeListener;
    private NetWorkInterface.OnGetHomaPageAdvertsListFourListener onGetHomaPageAdvertsListFourListener;
    private NetWorkInterface.OnGetHomaPageAdvertsListFiveListener onGetHomaPageAdvertsListFiveListener;
    private NetWorkInterface.OnGetLoadproductsListListener onGetLoadproductsListListener;
    private NetWorkInterface.OnGetHomaLoadTypeListListener onGetHomaLoadTypeListListener;
    private NetWorkInterface.OnGetHomaLoadTypeListener OnGetHomaLoadTypeListener;
    private NetWorkInterface.OnGetClassificationLeftDataListener onGetClassificationLeftDataListener;
    private NetWorkInterface.OnGetClassificationRightDataListener onGetClassificationRightDataListener;
    private NetWorkInterface.OnGetStoreDataListener onGetStoreDataListener;
    private NetWorkInterface.OnGetStoreDetailsDataListener onGetStoreDetailsDataListener;
    private NetWorkInterface.OnGetStoreDetails2DataListener onGetStoreDetails2DataListener;
    private NetWorkInterface.OnGetStoreDetails3DataListener onGetStoreDetails3DataListener;
    private NetWorkInterface.OnGetStoreDetailsCommodityDataListener onGetStoreDetailsCommodityDataListener;
    private NetWorkInterface.OnGetCommodityDetailsDataListener onGetCommodityDetailsDataListener;
    private NetWorkInterface.OnGetCommodityDetailsCommentListener onGetCommodityDetailsCommentListener;
    private NetWorkInterface.OnGetSelfStoreDataListener onGetSelfStoreDataListener;
    private NetWorkInterface.OnGetSelfStoreMoreDataListener onGetSelfStoreMoreDataListener;
    private NetWorkInterface.OnGetSearchDataListener onGetSearchDataListener;
    private NetWorkInterface.OnGetDailySaleListener onGetDailySaleListener;
    //便民服务
    private NetWorkInterface.OnGetConvenienceServicesBannerListener onGetConvenienceServicesBannerListener;
    private NetWorkInterface.OnGetConvenienceServicesListener onGetConvenienceServicesListener;
    private NetWorkInterface.OnGetConvenienceServicesDetailsListener onGetConvenienceServicesDetailsListener;
    private NetWorkInterface.OnGetMyConvenienceServicesListener onGetMyConvenienceServicesListener;
    private NetWorkInterface.OnGetMyServicesDetailsListener onGetMyServicesDetailsListener;
    private NetWorkInterface.OnGetReleaseServiceListener onGetReleaseServiceListener;
    private NetWorkInterface.OnDeleteMyConvenienceServicesListener onDeleteMyConvenienceServicesListener;
    private NetWorkInterface.OnGetMyServiceCategoryListener onGetMyServiceCategoryListener;
    private NetWorkInterface.OnGetServiceMobileListener onGetServiceMobileListener;
    //二手品
    private NetWorkInterface.OnGetSecondHandDivisionBannerListener onGetSecondHandDivisionBannerListener;
    private NetWorkInterface.OnGetSecondHandDivisionListener onGetSecondHandDivisionListener;
    private NetWorkInterface.OnGetSecondHandDivisionDetailsListener onGetSecondHandDivisionDetailsListener;
    private NetWorkInterface.OnGetMySecondHandDivisionListener onGetMySecondHandDivisionListener;
    private NetWorkInterface.OnGetMySecondHandDivisionDetailsListener onGetMySecondHandDivisionDetailsListener;
    private NetWorkInterface.OnGetReleaseSecondHandListener onGetReleaseSecondHandListener;
    private NetWorkInterface.OnDeleteMySecondHandDivisionListener onDeleteMySecondHandDivisionListener;
    private NetWorkInterface.OnGetSecondHandMobileListener onGetSecondHandMobileListener;
    //积分商城
    private NetWorkInterface.OnGetUserSignInListener onGetUserSignInListener;
    private NetWorkInterface.OnGetLoadAdvertsListener onGetLoadAdvertsListener;
    private NetWorkInterface.OnGetLoadCreditRankingListener onGetLoadCreditRankingListener;
    private NetWorkInterface.OnGetIntergalShopCommodityListener onGetIntergalShopCommodityListener;
    private NetWorkInterface.OnGetIntergalCommodityInfoListener onGetIntergalCommodityInfoListener;
    private NetWorkInterface.OnExchangeIntegralCommodityListener onExchangeIntegralCommodityListener;
    //优惠券
    private NetWorkInterface.OnGetStoreCouponsListener onGetStoreCouponsListener;
    private NetWorkInterface.OnGetReceiveCouponsListener onGetReceiveCouponsListener;
    private NetWorkInterface.OnGetMyCouponsListener onGetMyCouponsListener;
    private NetWorkInterface.OnGetDeleteCouponListener onGetDeleteCouponListener;
    //消息
    private NetWorkInterface.OnGetConsumerUncheckMessageListener onGetConsumerUncheckMessageListener;
    private NetWorkInterface.OnGetMerchantUncheckMessageListener onGetMerchantUncheckMessageListener;
    //上传图片
    private NetWorkInterface.OnGetUploadImageListener onGetUploadImageListener;
    //收藏
    private NetWorkInterface.OnGetMyFavoriteListener onGetMyFavoriteListener;
    private NetWorkInterface.OnGetEditCollectListener onGetEditCollectListener;

    /**
     * 构造函数
     **/
    private NetWorkOperate() {
        mOkHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
        mGson = new Gson();
    }

    public NetWorkOperate(Context context) {
        this();
        listener = (Listener) context;
    }

    public NetWorkOperate(Fragment fragment) {
        this();
        context = fragment.getContext();
        listener = (Listener) fragment;
    }

    /**
     * 对外方法
     */
    //初始化令牌-->1001
    public void getInitialToken(String model, String imei) {
        try {
            onGetInitialTokenListener = (NetWorkInterface.OnGetInitialTokenListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/init2?" +
                        "apptype=" + APPTYPE +
                        "&version=" + VERSION +
                        "&model=" + model +
                        "&imei=" + imei).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanGetToken mJsonBeanGetToken = mGson.fromJson(strJson, JsonBeanGetToken.class);

                Message message = new Message();

                if (mJsonBeanGetToken.getRet().equals("0")) {
                    message.arg1 = 1001;
                    message.obj = mJsonBeanGetToken;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanGetToken.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //写入用户名和密码-->1002
    public void getNewToken(String token, String userName, String password) {
        try {
            onGetNewTokenListener = (NetWorkInterface.OnGetNewTokenListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/SetUsersInfo?" +
                        "username=" + userName +
                        "&password=" + password +
                        "&access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanGetToken mJsonBeanGetToken = mGson.fromJson(strJson, JsonBeanGetToken.class);
                Message message = new Message();
                if (mJsonBeanGetToken.getRet().equals("0")) {
                    message.arg1 = 1002;
                    message.obj = mJsonBeanGetToken;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanGetToken.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //判断是否登录-->1003
    public void getLoinState(String token) {
        try {
            onGetLoginStateListener = (NetWorkInterface.OnGetLoginStateListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/HasLogin?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanLoginState mjsonBeanHasLogin = mGson.fromJson(strJson, JsonBeanLoginState.class);
                Message message = new Message();
                if (mjsonBeanHasLogin.getRet().equals("0")) {
                    message.arg1 = 1003;
                    message.obj = mjsonBeanHasLogin;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanHasLogin.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取用户注册验证码-->1004
    public void getUserRegisterCode(String token, String mobile) {
        try {
            onGetUserRegisterCodeListener = (NetWorkInterface.OnGetUserRegistrCodeListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/Registverification?access_token=" + token
                        + "&mobile=" + mobile
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanRegisterCode mjsonBeanRegister = mGson.fromJson(strJson, JsonBeanRegisterCode.class);
                Message message = new Message();
                if (mjsonBeanRegister.getRet().equals("0")) {
                    message.arg1 = 1004;
                    message.obj = mjsonBeanRegister;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanRegister.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //用户注册-->1005
    public void getUserRegister(String token, String mobile, String password, String verificationcode) {
        try {
            onGetUserRegisterListener = (NetWorkInterface.OnGetUserRegisterListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/Register?access_token=" + token
                        + "&mobile=" + mobile
                        + "&password=" + password
                        + "&verificationcode=" + verificationcode)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanUserRegister mjsonBeanUserRegister = mGson.fromJson(strJson, JsonBeanUserRegister.class);
                Message message = new Message();
                if (mjsonBeanUserRegister.getRet().equals("0")) {
                    message.arg1 = 1005;
                    message.obj = mjsonBeanUserRegister;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanUserRegister.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取找回密码验证码-->1006
    public void getForgetPasswordCode(String token, String mobile) {
        try {
            onGetForgetPasswordCodeListener = (NetWorkInterface.OnGetForgetPasswordCodeListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/Getbackpasswordverification?" +
                        "access_token=" + token +
                        "&mobile=" + mobile
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                JsonBeanForgetPasswordCode mjsonBeanForgetPasswordCode = mGson.fromJson(strJson, JsonBeanForgetPasswordCode.class);
                Message message = new Message();
                if (mjsonBeanForgetPasswordCode.getRet().equals("0")) {
                    message.arg1 = 1006;
                    message.obj = mjsonBeanForgetPasswordCode;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanForgetPasswordCode.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //找回密码-->1007
    public void getForgetPassword(String token, String mobile, String newpassword, String verificationcode) {
        try {
            onGetForgetPasswordListener = (NetWorkInterface.OnGetForgetPasswordListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/ResetPassword?" +
                        "access_token=" + token +
                        "&mobile=" + mobile +
                        "&newpassword=" + newpassword +
                        "&verificationcode=" + verificationcode)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                JsonBeanForgetPassword mjsonBeanForgetPassword = mGson.fromJson(strJson, JsonBeanForgetPassword.class);
                Message message = new Message();
                if (mjsonBeanForgetPassword.getRet().equals("0")) {
                    message.arg1 = 1007;
                    message.obj = mjsonBeanForgetPassword;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanForgetPassword.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //修改密码-->1008
    public void getUpdatePassWord(String token, String oldpassword, String newpassword) {
        try {
            onGetUpdatePassWordListener = (NetWorkInterface.OnGetUpdatePassWordListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/UpdatePassword?access_token=" + token
                        + "&oldpassword=" + oldpassword
                        + "&newpassword=" + newpassword)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanUpdatePassWord mjsonBeanUpdatePassWord = mGson.fromJson(strJson, JsonBeanUpdatePassWord.class);
                Message message = new Message();
                if (mjsonBeanUpdatePassWord.getRet().equals("0")) {
                    message.arg1 = 1008;
                    message.obj = mjsonBeanUpdatePassWord;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanUpdatePassWord.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取用户信息-->1009
    public void getUserInformation(String token, String position) {
        try {
            onGetUserInformationListener = (NetWorkInterface.OnGetUserInformationListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        if (position.equals("1")) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/auth/Information?access_token=" + token)
                    .build();
        } else if (position.equals("2")) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/auth/Information2?access_token=" + token)
                    .build();
        }

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanUserInformationData mjsonBeanUserInformationData = mGson.fromJson(strJson, JsonBeanUserInformationData.class);
                Message message = new Message();
                if (mjsonBeanUserInformationData.getRet().equals("0")) {
                    message.arg1 = 1009;
                    message.obj = mjsonBeanUserInformationData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanUserInformationData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //设置令牌经纬度信息，方法的重载-->1010
    public void getCoordinateToken(String token, String latitude, String longitude) {
        try {
            onGetNewTokenListener = (NetWorkInterface.OnGetNewTokenListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/SetCoordinate?" +
                        "coordinate=" + latitude + "," + longitude +
                        "&access_token=" + token
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanGetToken mJsonBeanGetToken = mGson.fromJson(strJson, JsonBeanGetToken.class);
                Message message = new Message();
                if (mJsonBeanGetToken.getRet().equals("0")) {
                    message.arg1 = 1010;
                    message.obj = mJsonBeanGetToken;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanGetToken.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //加载区域信息-->1011
    public void getRegionalData(String token) {
        try {
            onGetRegionalDataListener = (NetWorkInterface.OnGetRegionalDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/region/Load?access_token=" + token
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanRegionalData mJsonBeanRegionalData = mGson.fromJson(strJson, JsonBeanRegionalData.class);
                Message message = new Message();
                if (mJsonBeanRegionalData.getRet().equals("0")) {
                    message.arg1 = 1011;
                    message.obj = mJsonBeanRegionalData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanRegionalData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //修改用户名-->1012
    public void modifyUserName(String token, String username) {
        try {
            onModifyUserNameListener = (NetWorkInterface.OnModifyUserNameListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/UpdateUsername?access_token=" + token
                        + "&usersname=" + username
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanModifyUserName mJsonBeanModifyUserName = mGson.fromJson(strJson, JsonBeanModifyUserName.class);
                Message message = new Message();
                if (mJsonBeanModifyUserName.getRet().equals("0")) {
                    message.arg1 = 1012;
                    message.obj = mJsonBeanModifyUserName;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanModifyUserName.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //修改用户头像-->1013
    public void modifyUserPhoto(String token, String media_ids) {
        try {
            onModifyUserPhotoListener = (NetWorkInterface.OnModifyUserPhotoListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/UpdateAvatar?access_token=" + token
                        + "&media_ids=" + media_ids
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanModifyUserPhoto mJsonBeanModifyUserPhoto = mGson.fromJson(strJson, JsonBeanModifyUserPhoto.class);
                Message message = new Message();
                if (mJsonBeanModifyUserPhoto.getRet().equals("0")) {
                    message.arg1 = 1013;
                    message.obj = mJsonBeanModifyUserPhoto;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanModifyUserPhoto.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //写入定位区域,对应case-->1014
    public void getNewToken(String token, String regionname) {
        try {
            onGetNewTokenListener = (NetWorkInterface.OnGetNewTokenListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/SetRegionInfo?" +
                        "regionname=" + regionname +
                        "&access_token=" + token
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanGetToken mJsonBeanGetToken = mGson.fromJson(strJson, JsonBeanGetToken.class);
                Message message = new Message();
                if (mJsonBeanGetToken.getRet().equals("0")) {
                    message.arg1 = 1014;
                    message.obj = mJsonBeanGetToken;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanGetToken.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //修改用户头像-->1013
    public void getCurrentVersion() {
        try {
            onGetCurrentVersionListener = (NetWorkInterface.OnGetCurrentVersionListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/version/to"
                ).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanCurrentVersion mJsonBeanCurrentVersion = mGson.fromJson(strJson, JsonBeanCurrentVersion.class);
                Message message = new Message();
                message.arg1 = 1015;
                message.obj = mJsonBeanCurrentVersion;
                handler.sendMessage(message);
            }
        });
    }

    //获取我的订单-->2001
    public void getMyAllOrderData(String token, int currentpage) {
        try {
            onGetMyAllOrderDataListener = (NetWorkInterface.OnGetMyAllOrderDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/Load?" +
                        "access_token=" + token +
                        "&currentpage=" + currentpage)
                .build();

        Log(mRequest.url().toString());
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyAllOrder mJsonBeanMyAllOrder = mGson.fromJson(strJson, JsonBeanMyAllOrder.class);
                Message message = new Message();
                if (mJsonBeanMyAllOrder.getRet().equals("0")) {
                    message.arg1 = 2001;
                    message.obj = mJsonBeanMyAllOrder;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMyAllOrder.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取订单详情-->2002
    public void getOrderDetailsData(String token, String oId) {
        try {
            onGetOrderDetailsDataListener = (NetWorkInterface.OnGetOrderDetailsDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/LoadInfo?" +
                        "access_token=" + token +
                        "&oid=" + oId)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanOrderDetailsData mJsonBeanOrderDetailsData = mGson.fromJson(strJson, JsonBeanOrderDetailsData.class);
                Message message = new Message();
                if (mJsonBeanOrderDetailsData.getRet().equals("0")) {
                    message.arg1 = 2002;
                    message.obj = mJsonBeanOrderDetailsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanOrderDetailsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //添加订单-->2003
    public void addOrder(String token, String addressId, String timeid, String orderInfo,
                         String buyerremark, String couponid) {
        try {
            onAddOrderListener = (NetWorkInterface.OnAddOrderListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/SubmitOrder?" +
                        "access_token=" + token +
                        "&addressid=" + addressId +
                        "&autotimeid=" + timeid +
                        "&order_info=" + orderInfo +
                        "&buyerremark=" + buyerremark +
                        "&couponid=" + couponid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAddOrder mJsonBeanAddOrder = mGson.fromJson(strJson, JsonBeanAddOrder.class);
                Message message = new Message();
                if (mJsonBeanAddOrder.getRet().equals("0")) {
                    message.arg1 = 2003;
                    message.obj = mJsonBeanAddOrder;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanAddOrder.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取支付字窜-->2004
    public void getAliPayText(String token, String paytype, String sntype, String osn) {
        try {
            onGetAliPayTextListener = (NetWorkInterface.OnGetAliPayTextListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/pay/SignOrder?" +
                        "access_token=" + token +
                        "&paytype=" + paytype +
                        "&sntype=" + sntype +
                        "&sn=" + osn)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAliPayText mJsonBeanAliPayText = mGson.fromJson(strJson, JsonBeanAliPayText.class);
                Message message = new Message();
                if (mJsonBeanAliPayText.getRet().equals("0")) {
                    message.arg1 = 2004;
                    message.obj = mJsonBeanAliPayText;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanAliPayText.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //我的订单，去评价-->2005
    public void getGoEvaluate(String token, String pid, String star1, String star2, String star3,
                              String message, String media_ids) {
        try {
            onGetGoEvaluateListner = (NetWorkInterface.OnGetGoEvaluateListner) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/productreviews/Add?" +
                        "access_token=" + token +
                        "&pid=" + pid +
                        "&star1=" + star1 +
                        "&star2=" + star2 +
                        "&star3=" + star3 +
                        "&message=" + message +
                        "&media_ids=" + media_ids
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanGoEvaluate mjsonBeanGoEvaluate = mGson.fromJson(strJson, JsonBeanGoEvaluate.class);
                Message message = new Message();
                if (mjsonBeanGoEvaluate.getRet().equals("0")) {
                    message.arg1 = 2005;
                    message.obj = mjsonBeanGoEvaluate;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanGoEvaluate.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //取消订单-->2006
    public void getCancelOrder(String token, String oid) {
        try {
            onGetCancelOrderListener = (NetWorkInterface.OnGetCancelOrderListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/Cancel?access_token=" + token
                        + "&oid=" + oid

                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanCancelOrder mjsonBeanCancelOrder = mGson.fromJson(strJson,
                        JsonBeanCancelOrder.class);
                Message message = new Message();
                if (mjsonBeanCancelOrder.getRet().equals("0")) {
                    message.arg1 = 2006;
                    message.obj = mjsonBeanCancelOrder;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanCancelOrder.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //申请退款-->2007
    public void getApplyRefund(String token, String oId, String reason, String mark) {
        try {
            onGetApplyRefundListener = (NetWorkInterface.OnGetApplyRefundListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/Refund?" +
                        "access_token=" + token +
                        "&oid=" + oId +
                        "&refund_reason=" + reason +
                        "&refund_mark=" + mark)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanApplyRefund mJsonBeanApplyRefund = mGson.fromJson(strJson, JsonBeanApplyRefund.class);
                Message message = new Message();
                if (mJsonBeanApplyRefund.getRet().equals("0")) {
                    message.arg1 = 2007;
                    message.obj = mJsonBeanApplyRefund;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanApplyRefund.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //判断是否显示商户订单-->2008
    public void getShow(String token) {
        try {
            onGetShowListener = (NetWorkInterface.OnGetShowListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/menu/Show?" +
                        "access_token=" + token
                ).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanGetShow mjsonBeanGetShow = mGson.fromJson(strJson, JsonBeanGetShow.class);
                Message message = new Message();
                if (mjsonBeanGetShow.getRet().equals("0")) {
                    message.arg1 = 2008;
                    message.obj = mjsonBeanGetShow;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanGetShow.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商户订单-->2009
    public void getMerchantOrderData(String token, String state, int currentpage) {
        try {
            onGetMerChantOrderDataListener = (NetWorkInterface.OnGetMerChantOrderDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        if (state.equals("0")) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/storesorders/Load?" +
                            "access_token=" + token +
                            "&currentpage=" + currentpage)
                    .build();
        } else if (state.equals("2")) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/storesorders/LoadDistribution?" +
                            "access_token=" + token +
                            "&currentpage=" + currentpage)
                    .build();
        }
        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMerchantOrder mJsonBeanMerchantOrder = mGson.fromJson(strJson, JsonBeanMerchantOrder.class);
                Message message = new Message();
                if (mJsonBeanMerchantOrder.getRet().equals("0")) {
                    message.arg1 = 2009;
                    message.obj = mJsonBeanMerchantOrder;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMerchantOrder.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商户订单详情-->2010
    public void getMerchantOrderDetailsData(String token, String oId) {
        try {
            onGetMechantOrderDetailsDataListener = (NetWorkInterface.OnGetMechantOrderDetailsDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/storesorders/LoadInfo?" +
                        "access_token=" + token +
                        "&oid=" + oId)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMerchantOrderDetailsData mJsonBeanMerchantOrderDetailsData = mGson.fromJson(strJson, JsonBeanMerchantOrderDetailsData.class);
                Message message = new Message();
                if (mJsonBeanMerchantOrderDetailsData.getRet().equals("0")) {
                    message.arg1 = 2010;
                    message.obj = mJsonBeanMerchantOrderDetailsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMerchantOrderDetailsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //删除我的订单-->2011
    public void deleteMyOrder(String token, String oId) {
        try {
            onDeleteMyOrderlistener = (NetWorkInterface.OnDeleteMyOrderlistener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/Delete?" +
                        "access_token=" + token +
                        "&oid=" + oId)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDeleteMyOrder mJsonBeanDeleteMyOrder = mGson.fromJson(strJson, JsonBeanDeleteMyOrder.class);
                Message message = new Message();
                if (mJsonBeanDeleteMyOrder.getRet().equals("0")) {
                    message.arg1 = 2011;
                    message.obj = mJsonBeanDeleteMyOrder;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanDeleteMyOrder.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取去评论商品列表-->2012
    public void getEvaluateProduct(String token, String oId) {
        try {
            onGetEvaluateProductListener = (NetWorkInterface.OnGetEvaluateProductListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/LoadreViewsInfo?" +
                        "access_token=" + token +
                        "&oid=" + oId)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanEvaluateProduct mJsonBeanEvaluateProduct = mGson.fromJson(strJson, JsonBeanEvaluateProduct.class);
                Message message = new Message();
                if (mJsonBeanEvaluateProduct.getRet().equals("0")) {
                    message.arg1 = 2012;
                    message.obj = mJsonBeanEvaluateProduct;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanEvaluateProduct.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商品更多评论列表-->2013
    public void getMoreComment(String token, String pid, String currentPage) {
        try {
            onGetMoreCommentListener = (NetWorkInterface.OnGetMoreCommentListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/productreviews/LoadBypid?" +
                        "access_token=" + token +
                        "&pid=" + pid +
                        "&currentpage=" + currentPage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMoreComment mJsonBeanMoreComment = mGson.fromJson(strJson, JsonBeanMoreComment.class);
                Message message = new Message();
                if (mJsonBeanMoreComment.getRet().equals("0")) {
                    message.arg1 = 2013;
                    message.obj = mJsonBeanMoreComment;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMoreComment.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取我的评论列表-->2014
    public void getMyComment(String token, String currentPage) {
        try {
            onGetMyCommentListener = (NetWorkInterface.OnGetMyCommentListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/productreviews/LoadByuid?" +
                        "access_token=" + token +
                        "&currentpage=" + currentPage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyComment mJsonBeanMyComment = mGson.fromJson(strJson, JsonBeanMyComment.class);
                Message message = new Message();
                if (mJsonBeanMyComment.getRet().equals("0")) {
                    message.arg1 = 2014;
                    message.obj = mJsonBeanMyComment;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMyComment.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //提交我的评价-->2015
    public void submitEvaluation(String json) {
        try {
            onSubmitEvaluationListener = (NetWorkInterface.OnSubmitEvaluationListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        RequestBody formBody = new FormBody.Builder().add("data", json).build();
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/productreviews/AddReviews")
                .post(formBody)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSubmitEvaluation mJsonBeanSubmitEvaluation = mGson.fromJson(strJson, JsonBeanSubmitEvaluation.class);
                Message message = new Message();
                if (mJsonBeanSubmitEvaluation.getRet().equals("0")) {
                    message.arg1 = 2015;
                    message.obj = mJsonBeanSubmitEvaluation;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSubmitEvaluation.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取配送时间-->2016
    public void getDeliveryTime(String token, String storeId) {
        try {
            onGetDeliveryTimeListener = (NetWorkInterface.OnGetDeliveryTimeListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/distributiontime/Load?access_token=" + token + "&storeid=" + storeId)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDeliveryTime mJsonBeanDeliveryTime = mGson.fromJson(strJson, JsonBeanDeliveryTime.class);
                Message message = new Message();
                if (mJsonBeanDeliveryTime.getRet().equals("0")) {
                    message.arg1 = 2016;
                    message.obj = mJsonBeanDeliveryTime;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanDeliveryTime.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //商户订单按钮编辑-->2017
    public void getMerchantOrderEdit(String token, String oid) {
        try {
            onGetMerchantOrderEditListener = (NetWorkInterface.OnGetMerchantOrderEditListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/storesorders/EditOrder?" +
                        "access_token=" + token +
                        "&oid=" + oid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMerchantOrderEdit mJsonBeanMerchantOrderEdit = mGson.fromJson(strJson, JsonBeanMerchantOrderEdit.class);
                Message message = new Message();
                if (mJsonBeanMerchantOrderEdit.getRet().equals("0")) {
                    message.arg1 = 2017;
                    message.obj = mJsonBeanMerchantOrderEdit;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMerchantOrderEdit.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //退款原因-->2018
    public void getRefundReason(String token, String oid) {
        try {
            onGetRefundReasonListener = (NetWorkInterface.OnGetRefundReasonListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/orders/Refund_reason?" +
                        "access_token=" + token +
                        "&oid=" + oid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanRefundReason mJsonBeanRefundReason = mGson.fromJson(strJson, JsonBeanRefundReason.class);
                Message message = new Message();
                if (mJsonBeanRefundReason.getRet().equals("0")) {
                    message.arg1 = 2018;
                    message.obj = mJsonBeanRefundReason;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanRefundReason.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取优惠券-->2019
    public void getCoupon(String token, String storeId) {
        try {
            onGetCouponListener = (NetWorkInterface.OnGetCouponListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/coupons/LoadOrderstore?access_token=" + token + "&storeid=" + storeId)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                SettlemCoupons mJsonBeanDeliveryTime = mGson.fromJson(strJson, SettlemCoupons.class);
                Message message = new Message();
                if (mJsonBeanDeliveryTime.getRet().equals("0")) {
                    message.arg1 = 2019;
                    message.obj = mJsonBeanDeliveryTime;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanDeliveryTime.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //加载配送地址-->3001
    public void getAddress(String token) {
        try {
            onLoadAddressListener = (NetWorkInterface.OnLoadAddressListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/shipaddresses/Load?" +
                        "access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanLoadAddress mJsonBeanLoadAddress = mGson.fromJson(strJson, JsonBeanLoadAddress.class);
                Message message = new Message();
                if (mJsonBeanLoadAddress.getRet().equals("0")) {
                    message.arg1 = 3001;
                    message.obj = mJsonBeanLoadAddress;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanLoadAddress.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取附近地址-->3002
    public void getNearbyAddress(String token, String currentPage) {
        try {
            onGetNearbyAddressListener = (NetWorkInterface.OnGetNearbyAddressListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/placesearch/Load?" +
                        "access_token=" + token +
                        "&currentpage=" + currentPage
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanNearbyAddress mJsonBeanNearbyAddress = mGson.fromJson(strJson, JsonBeanNearbyAddress.class);
                Message message = new Message();
                if (mJsonBeanNearbyAddress.getRet().equals("0")) {
                    message.arg1 = 3002;
                    message.obj = mJsonBeanNearbyAddress;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanNearbyAddress.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //添加配送地址-->3003
    public void addAddress(String token, String isdefault, String name, String phone, String address, String addressmark) {
        try {
            onAddAddressListener = (NetWorkInterface.OnAddAddressListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/shipaddresses/Add?" +
                        "access_token=" + token +
                        "&isdefault=" + isdefault +
                        "&consignee=" + name +
                        "&mobile=" + phone +
                        "&address=" + address +
                        "&addressmark=" + addressmark)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAddAddress mJsonBeanAddAddress = mGson.fromJson(strJson, JsonBeanAddAddress.class);
                Message message = new Message();
                if (mJsonBeanAddAddress.getRet().equals("0")) {
                    message.arg1 = 3003;
                    message.obj = mJsonBeanAddAddress;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanAddAddress.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //修改配送地址-->3004
    public void editAddress(String token, String said, String isdefault, String name, String phone, String address, String addressmark) {
        try {
            onEditAddressListener = (NetWorkInterface.OnEditAddressListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/shipaddresses/Update?" +
                        "access_token=" + token +
                        "&said=" + said +
                        "&isdefault=" + isdefault +
                        "&consignee=" + name +
                        "&mobile=" + phone +
                        "&address=" + address +
                        "&addressmark=" + addressmark)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanEditAddress mJsonBeanEditAddress = mGson.fromJson(strJson, JsonBeanEditAddress.class);
                Message message = new Message();
                if (mJsonBeanEditAddress.getRet().equals("0")) {
                    message.arg1 = 3004;
                    message.obj = mJsonBeanEditAddress;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanEditAddress.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //删除配送地址-->3005
    public void deleteAddress(String token, String said) {
        try {
            onDeleteAddressListener = (NetWorkInterface.OnDeleteAddressListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/shipaddresses/Delete?" +
                        "access_token=" + token +
                        "&said=" + said
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDeleteAddress mJsonBeanDeleteAddress = mGson.fromJson(strJson, JsonBeanDeleteAddress.class);
                Message message = new Message();
                if (mJsonBeanDeleteAddress.getRet().equals("0")) {
                    message.arg1 = 3005;
                    message.obj = mJsonBeanDeleteAddress;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanDeleteAddress.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页数据-->4001
    public void getHomaPageData(String token) {
        try {
            onGetHomaPageDataListener = (NetWorkInterface.OnGetHomaPageDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/home/Load?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanHomePage mJsonBeanHomePage = mGson.fromJson(strJson, JsonBeanHomePage.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4001;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页第一个轮播图下方数据-->4014
    public void getHomaPageAdvertsListOne(String token) {
        try {
            onGetHomaPageAdvertsListOneListener = (NetWorkInterface.OnGetHomaPageAdvertsListOneListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/Adverts/List?access_token=" + token + "&type=0")
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAdverts mJsonBeanHomePage = mGson.fromJson(strJson, JsonBeanAdverts.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4014;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页第二个轮播图数据-->4015
    public void getHomaPageAdvertsListTwo(String token) {
        try {
            onGetHomaPageAdvertsListTwoListener = (NetWorkInterface.OnGetHomaPageAdvertsListTwoListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/Adverts/List?access_token=" + token + "&type=1")
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAdverts mJsonBeanHomePage = mGson.fromJson(strJson, JsonBeanAdverts.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4015;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页第二个轮播图数据-->4016
    public void getHomaPageAdvertsListThree(String token) {
        try {
            onGetHomaPageAdvertsListThreeListener = (NetWorkInterface.OnGetHomaPageAdvertsListThreeListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/Adverts/List?access_token=" + token + "&type=2")
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAdverts mJsonBeanHomePage = mGson.fromJson(strJson, JsonBeanAdverts.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4016;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页第二个轮播图数据-->4017
    public void getHomaPageAdvertsListFour(String token) {
        try {
            onGetHomaPageAdvertsListFourListener = (NetWorkInterface.OnGetHomaPageAdvertsListFourListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/Adverts/List?access_token=" + token + "&type=3")
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAdverts mJsonBeanHomePage = mGson.fromJson(strJson, JsonBeanAdverts.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4017;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页第二个轮播图数据-->4020
    public void getHomaPageAdvertsListFive(String token) {
        try {
            onGetHomaPageAdvertsListFiveListener = (NetWorkInterface.OnGetHomaPageAdvertsListFiveListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/Adverts/List?access_token=" + token + "&type=4")
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanAdverts mJsonBeanHomePage = mGson.fromJson(strJson, JsonBeanAdverts.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4020;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //附近店铺菜单数据 api/home2/LoadType
    //获取首页第二个轮播图数据-->4018
    public void getHomaLoadType(String token) {
        try {
            OnGetHomaLoadTypeListener = (NetWorkInterface.OnGetHomaLoadTypeListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/home2/LoadType?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(strJson);
                    List<HorizontaiListviewData> datas = new ArrayList<HorizontaiListviewData>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        HorizontaiListviewData horizontaiListviewData = new HorizontaiListviewData();
                        horizontaiListviewData.setName(object.getString("name"));
                        horizontaiListviewData.setId(object.getString("id"));
                        datas.add(horizontaiListviewData);
                    }
                    Message message = new Message();
//                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4018;
                    message.obj = datas;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("Tag", "json-->" + strJson);
                //   HorizontaiListviewData mJsonBeanHomePage = mGson.fromJson(strJson, HorizontaiListviewData.class);

//                } else {
//                    strMessage = mJsonBeanHomePage.getMsg();
//                    message.arg1 = 0;
//                    handler.sendMessage(message);
//                }
            }
        });
    }

    //附近店铺菜单数据 api/home2/LoadType
    //获取首页精品推荐轮播图数据-->4021
    public void getHomaLoadproducts(String token) {
        try {
            onGetLoadproductsListListener = (NetWorkInterface.OnGetLoadproductsListListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/home2/Loadproducts_best?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                HomaLoadproductsData mJsonBeanHomePage = mGson.fromJson(strJson, HomaLoadproductsData.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4021;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取首页店铺数据-->4019
    public void getHomaLoadTypeList(String token, String type) {
        try {
            onGetHomaLoadTypeListListener = (NetWorkInterface.OnGetHomaLoadTypeListListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/home2/Load?access_token=" + token + "&type=" + type)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                HorizontaiListviewListData mJsonBeanHomePage = mGson.fromJson(strJson, HorizontaiListviewListData.class);
                Message message = new Message();
                if (mJsonBeanHomePage.getRet().equals("0")) {
                    message.arg1 = 4019;
                    message.obj = mJsonBeanHomePage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanHomePage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取分类左边数据-->4002
    public void getClassificationLeftData(String token) {
        try {
            onGetClassificationLeftDataListener = (NetWorkInterface.OnGetClassificationLeftDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/categories2/Load?access_token=" + token
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanClassificationLeft mjsonBeanClassificationLeft = mGson.fromJson(strJson, JsonBeanClassificationLeft.class);
                Message message = new Message();
                if (mjsonBeanClassificationLeft.getRet().equals("0")) {
                    message.arg1 = 4002;
                    message.obj = mjsonBeanClassificationLeft;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanClassificationLeft.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取分类右边数据-->4003
    public void getClassificationRightData(String token, String cateid) {
        try {
            onGetClassificationRightDataListener = (NetWorkInterface.OnGetClassificationRightDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/categories2/Loadsub?access_token=" + token
                        + "&cateid=" + cateid
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanClassificationRightData mjsonBeanClassificationRightData = mGson.fromJson(strJson,
                        JsonBeanClassificationRightData.class);
                Message message = new Message();
                if (mjsonBeanClassificationRightData.getRet().equals("0")) {
                    message.arg1 = 4003;
                    message.obj = mjsonBeanClassificationRightData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanClassificationRightData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取店铺数据-->4004
    public void getStoreData(String token, String storeSate, String storeClass, String storeOrder, String currentPage) {
        try {
            onGetStoreDataListener = (NetWorkInterface.OnGetStoreDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/stores/Load?" +
                        "access_token=" + token +
                        "&storesate=" + storeSate +
                        "&storeclasses=" + storeClass +
                        "&storeorder=" + storeOrder +
                        "&currentpage=" + currentPage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                JsonBeanStoreData mJsonBeanStoreData = mGson.fromJson(strJson, JsonBeanStoreData.class);
                Message message = new Message();
                if (mJsonBeanStoreData.getRet().equals("0")) {
                    message.arg1 = 4004;
                    message.obj = mJsonBeanStoreData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanStoreData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商店详细信息->4005
    public void getStoreDetailsData(String token, String storeid, String sate) {
        try {
            onGetStoreDetailsDataListener = (NetWorkInterface.OnGetStoreDetailsDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        if (sate == null) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/storesinfo/Load?" +
                            "access_token=" + token +
                            "&storeid=" + storeid
                    ).build();
        } else {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/storesinfo/Load?" +
                            "access_token=" + token +
                            "&storeid=" + storeid
                            + "&sate=" + sate

                    ).build();
        }
        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanStoreDetailsData mJsonBeanStoreDetailsData = mGson.fromJson(strJson, JsonBeanStoreDetailsData.class);
                Message message = new Message();
                if (mJsonBeanStoreDetailsData.getRet().equals("0")) {
                    message.arg1 = 4005;
                    message.obj = mJsonBeanStoreDetailsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanStoreDetailsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商店详细信息->4023
    public void getStoreDetails3Data(String token, String storeid) {
        try {
            onGetStoreDetails3DataListener = (NetWorkInterface.OnGetStoreDetails3DataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/storesinfo2/LoadInfo?" +
                        "access_token=" + token +
                        "&storeid=" + storeid
                ).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                StoresBaseInfoData mJsonBeanStoreDetailsData = mGson.fromJson(strJson, StoresBaseInfoData.class);
                Message message = new Message();
                if (mJsonBeanStoreDetailsData.getRet().equals("0")) {
                    message.arg1 = 4023;
                    message.obj = mJsonBeanStoreDetailsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanStoreDetailsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }


    //获取商店详细信息->4022
    public void getStoreDetails2Data(String token, String storeid, String sate) {
        try {
            onGetStoreDetails2DataListener = (NetWorkInterface.OnGetStoreDetails2DataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        if (sate == null) {
            if (storeid.equals("14")) {
                mRequest = new Request.Builder()
                        .url(ADDRESS + "/api/storesinfo3/Load?" +
                                "access_token=" + token +
                                "&storeid=" + storeid + "&cateid=" + sate
                        ).build();
            } else {
                mRequest = new Request.Builder()
                        .url(ADDRESS + "/api/storesinfo2/Load?" +
                                "access_token=" + token +
                                "&storeid=" + storeid
                        ).build();
            }

        } else {
            if (storeid.equals("14")) {
                mRequest = new Request.Builder()
                        .url(ADDRESS + "/api/storesinfo3/Load?" +
                                "access_token=" + token +
                                "&storeid=" + storeid + "&cateid=" + sate
                        ).build();
            } else {
                mRequest = new Request.Builder()
                        .url(ADDRESS + "/api/storesinfo2/Load?" +
                                "access_token=" + token +
                                "&storeid=" + storeid
                        ).build();
            }
        }
        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                StoresinfoData mJsonBeanStoreDetailsData = mGson.fromJson(strJson, StoresinfoData.class);
                Message message = new Message();
                if (mJsonBeanStoreDetailsData.getRet().equals("0")) {
                    message.arg1 = 4022;
                    message.obj = mJsonBeanStoreDetailsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanStoreDetailsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取店铺详情右边的商品数据-->4006
    public void getStoreDetailsCommodityData(String token, String storeid, String cateid, String searchKey, String sate) {
        try {
            onGetStoreDetailsCommodityDataListener = (NetWorkInterface.OnGetStoreDetailsCommodityDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        if (sate == null) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/storesinfo/LoadProducts?" +
                            "access_token=" + token +
                            "&storeid=" + storeid +
                            "&cateid=" + cateid +
                            "&searchkey=" + searchKey)
                    .build();
        } else {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/storesinfo/LoadProducts?" +
                            "access_token=" + token +
                            "&storeid=" + storeid +
                            "&cateid=" + cateid +
                            "&searchkey=" + searchKey
                            + "&sate=" + sate
                    )
                    .build();
        }

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanStoreDetailsCommodityData mJsonBeanStoreDetailsCommodityData = mGson.fromJson(strJson,
                        JsonBeanStoreDetailsCommodityData.class);
                Message message = new Message();
                if (mJsonBeanStoreDetailsCommodityData.getRet().equals("0")) {
                    message.arg1 = 4006;
                    message.obj = mJsonBeanStoreDetailsCommodityData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanStoreDetailsCommodityData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商品详情-->4007
    public void getCommodityDetailsData(String token, String pid) {
        try {
            onGetCommodityDetailsDataListener = (NetWorkInterface.OnGetCommodityDetailsDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/products/Load?" +
                        "access_token=" + token +
                        "&pid=" + pid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanCommodityDetailsData mJsonBeanCommodityDetailsData = mGson.fromJson(strJson, JsonBeanCommodityDetailsData.class);
                Message message = new Message();
                if (mJsonBeanCommodityDetailsData.getRet().equals("0")) {
                    message.arg1 = 4007;
                    message.obj = mJsonBeanCommodityDetailsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanCommodityDetailsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取商品详情里的评价信息-->4008
    public void getCommodityDetailsComment(String token, String pid) {
        try {
            onGetCommodityDetailsCommentListener = (NetWorkInterface.OnGetCommodityDetailsCommentListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/productreviews/Load?" +
                        "access_token=" + token +
                        "&pid=" + pid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanCommodityDetailsComment mJsonBeanCommodityDetailsComment = mGson.fromJson(strJson, JsonBeanCommodityDetailsComment.class);
                Message message = new Message();
                if (mJsonBeanCommodityDetailsComment.getRet().equals("0")) {
                    message.arg1 = 4008;
                    message.obj = mJsonBeanCommodityDetailsComment;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanCommodityDetailsComment.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取自营店数据-->4009
    public void getSelfStoreData(String token) {
        try {
            onGetSelfStoreDataListener = (NetWorkInterface.OnGetSelfStoreDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/SelfStores/Load?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSelfStoreData mJsonBeanSelfStoreData = mGson.fromJson(strJson, JsonBeanSelfStoreData.class);
                Message message = new Message();
                if (mJsonBeanSelfStoreData.getRet().equals("0")) {
                    message.arg1 = 4009;
                    message.obj = mJsonBeanSelfStoreData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSelfStoreData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取自营店更多页面数据-->4010
    public void getSelfStoreMoreData(String token, String urlType, String currentPage) {
        try {
            onGetSelfStoreMoreDataListener = (NetWorkInterface.OnGetSelfStoreMoreDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/SelfStores/Loadproducts" +
                        "?access_token=" + token +
                        "&urltype=" + urlType +
                        "&currentpage=" + currentPage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSelfStoreMoreData mJsonBeanSelfStoreMoreData = mGson.fromJson(strJson, JsonBeanSelfStoreMoreData.class);

                Message message = new Message();
                if (mJsonBeanSelfStoreMoreData.getRet().equals("0")) {
                    message.arg1 = 4010;
                    message.obj = mJsonBeanSelfStoreMoreData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSelfStoreMoreData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    /**
     * 获取检索结果数据
     * state = 1,根据关键字搜索根据catenumber搜索
     * state = 2,
     */
    public void getSearchData(String token, String searchKey, String state) {
        try {
            onGetSearchDataListener = (NetWorkInterface.OnGetSearchDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        if (state.equals("1")) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/search/Load?" +
                            "access_token=" + token +
                            "&searchkey=" + searchKey)
                    .build();
        } else if (state.equals("2")) {
            mRequest = new Request.Builder()
                    .url(ADDRESS + "/api/categories/LoadSearch?" +
                            "access_token=" + token +
                            "&catenumber=" + searchKey)
                    .build();
        }
        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSearhResultData mJsonBeanSearhResultData = mGson.fromJson(strJson, JsonBeanSearhResultData.class);
                Message message = new Message();
                if (mJsonBeanSearhResultData.getRet().equals("0")) {
                    message.arg1 = 4011;
                    message.obj = mJsonBeanSearhResultData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSearhResultData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取今日特价-->4013
    public void getDailySaleData(String token, String currentpage) {
        try {
            onGetDailySaleListener = (NetWorkInterface.OnGetDailySaleListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/productslower/Load?" +
                        "access_token=" + token +
                        "&currentpage=" + currentpage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDailySale mJsonBeanDailySale = mGson.fromJson(strJson, JsonBeanDailySale.class);
                Message message = new Message();
                if (mJsonBeanDailySale.getRet().equals("0")) {
                    message.arg1 = 4013;
                    message.obj = mJsonBeanDailySale;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanDailySale.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取便民服务首页广告-->5001
    public void getConvenienceServicesBanner(String token) {
        try {
            onGetConvenienceServicesBannerListener = (NetWorkInterface.OnGetConvenienceServicesBannerListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/myservice/Loadadverts?" +
                        "access_token=" + token
                ).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanConvenienceServicesBanner mJsonBeanConvenienceServicesBanner = mGson.fromJson(strJson, JsonBeanConvenienceServicesBanner.class);
                Message message = new Message();
                if (mJsonBeanConvenienceServicesBanner.getRet().equals("0")) {
                    message.arg1 = 5001;
                    message.obj = mJsonBeanConvenienceServicesBanner;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanConvenienceServicesBanner.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取便民服务资讯类列表-->5002
    public void getConvenienceServices(String token, String region, String sate, String cateid, String keyvalue, String currentpage) {
        try {
            onGetConvenienceServicesListener = (NetWorkInterface.OnGetConvenienceServicesListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/myservice/Loadnews?" +
                        "access_token=" + token +
                        "&region=" + region +
                        "&sate=" + sate +
                        "&cateid=" + cateid +
                        "&keyvalue=" + keyvalue +
                        "&currentpage=" + currentpage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanConvenienceServices mJsonBeanConvenienceServices = mGson.fromJson(strJson, JsonBeanConvenienceServices.class);
                Message message = new Message();
                if (mJsonBeanConvenienceServices.getRet().equals("0")) {
                    message.arg1 = 5002;
                    message.obj = mJsonBeanConvenienceServices;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanConvenienceServices.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取便民服务详细信息-->5003
    public void getConvenienceServicesDetails(String token, String newsid) {
        try {
            onGetConvenienceServicesDetailsListener = (NetWorkInterface.OnGetConvenienceServicesDetailsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/myservice/Loadinfo?" +
                        "access_token=" + token +
                        "&newsid=" + newsid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanConvenienceServicesDetails mJsonBeanConvenienceServicesDetails = mGson.fromJson(strJson, JsonBeanConvenienceServicesDetails.class);
                Message message = new Message();
                if (mJsonBeanConvenienceServicesDetails.getRet().equals("0")) {
                    message.arg1 = 5003;
                    message.obj = mJsonBeanConvenienceServicesDetails;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanConvenienceServicesDetails.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取我的便民服务-->5004
    public void getMyConvenienceServices(String token, String currentpage) {
        try {
            onGetMyConvenienceServicesListener = (NetWorkInterface.OnGetMyConvenienceServicesListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/usersmyservice/Loadnews?" +
                        "access_token=" + token +
                        "&currentpage=" + currentpage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyConvenienceServices mjsonBeanMyConvenienceServices = mGson.fromJson(strJson, JsonBeanMyConvenienceServices.class);
                Message message = new Message();
                if (mjsonBeanMyConvenienceServices.getRet().equals("0")) {
                    message.arg1 = 5004;
                    message.obj = mjsonBeanMyConvenienceServices;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanMyConvenienceServices.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //我的便民服务详情-->5005
    public void getMyServicesDetails(String token, String newsid) {
        try {
            onGetMyServicesDetailsListener = (NetWorkInterface.OnGetMyServicesDetailsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/usersmyservice/Loadinfo?" +
                        "access_token=" + token +
                        "&newsid=" + newsid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyServicesDetails mjsonBeanMyServicesDetails = mGson.fromJson(strJson, JsonBeanMyServicesDetails.class);
                Message message = new Message();
                if (mjsonBeanMyServicesDetails.getRet().equals("0")) {
                    message.arg1 = 5005;
                    message.obj = mjsonBeanMyServicesDetails;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanMyServicesDetails.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //发布我的便民服务-->5006
    public void getReleaseServices(String token, String name, String region, String sate, String cateid, String contacts, String mobile, String media_ids, String content) {
        try {
            onGetReleaseServiceListener = (NetWorkInterface.OnGetReleaseServiceListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/usersmyservice/Add?" +
                        "access_token=" + token +
                        "&name=" + name +
                        "&region=" + region +
                        "&sate=" + sate +
                        "&cateid=" + cateid +
                        "&contacts=" + contacts +
                        "&mobile=" + mobile +
                        "&media_ids=" + media_ids +
                        "&content=" + content)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                JsonBeanReleaseService mjsonBeanReleaseService = mGson.fromJson(strJson, JsonBeanReleaseService.class);
                Message message = new Message();
                if (mjsonBeanReleaseService.getRet().equals("0")) {
                    message.arg1 = 5006;
                    message.obj = mjsonBeanReleaseService;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanReleaseService.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //删除我的便民服务-->5007
    public void deleteMyConvenienceServices(String token, String newsid) {
        try {
            onDeleteMyConvenienceServicesListener = (NetWorkInterface.OnDeleteMyConvenienceServicesListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/usersmyservice/Delete?" +
                        "access_token=" + token +
                        "&newsid=" + newsid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDeleteMyConvenienceServices mjsonBeanDeleteMyConvenienceServices = mGson.fromJson(strJson, JsonBeanDeleteMyConvenienceServices.class);
                Message message = new Message();
                if (mjsonBeanDeleteMyConvenienceServices.getRet().equals("0")) {
                    message.arg1 = 5007;
                    message.obj = mjsonBeanDeleteMyConvenienceServices;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanDeleteMyConvenienceServices.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取我的便民服务类别-->5008
    public void getMyServiceCategory(String token) {
        try {
            onGetMyServiceCategoryListener = (NetWorkInterface.OnGetMyServiceCategoryListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/usersmyservice/Loadcate?" +
                        "access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyServiceCategory mJsonBeanMyServiceCategory = mGson.fromJson(strJson, JsonBeanMyServiceCategory.class);
                Message message = new Message();
                if (mJsonBeanMyServiceCategory.getRet().equals("0")) {
                    message.arg1 = 5008;
                    message.obj = mJsonBeanMyServiceCategory;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMyServiceCategory.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取便民服务详情联系人电话-->5009
    public void getServiceMobile(String token, String newsid) {
        try {
            onGetServiceMobileListener = (NetWorkInterface.OnGetServiceMobileListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/myservice/Creditsorder?" +
                        "access_token=" + token +
                        "&newsid=" + newsid
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanServiceMobile mJsonBeanServiceMobile = mGson.fromJson(strJson, JsonBeanServiceMobile.class);
                Message message = new Message();
                if (mJsonBeanServiceMobile.getRet().equals("0")) {
                    message.arg1 = 5009;
                    message.obj = mJsonBeanServiceMobile;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanServiceMobile.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }


    //获取二手品首页广告-->6001
    public void getSecondHandDivisionBanner(String token) {
        try {
            onGetSecondHandDivisionBannerListener = (NetWorkInterface.OnGetSecondHandDivisionBannerListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/secondhand/Loadadverts?" +
                        "access_token=" + token
                ).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSecondHandDivisionBanner mJsonBeanSecondHandDivisionBanner = mGson.fromJson(strJson, JsonBeanSecondHandDivisionBanner.class);
                Message message = new Message();
                if (mJsonBeanSecondHandDivisionBanner.getRet().equals("0")) {
                    message.arg1 = 6001;
                    message.obj = mJsonBeanSecondHandDivisionBanner;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSecondHandDivisionBanner.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取二手品首页产品数据-->6002
    public void getSecondHandDivision(String token, String region, String money, String sate, String keyvalue, String currentpage) {
        try {
            onGetSecondHandDivisionListener = (NetWorkInterface.OnGetSecondHandDivisionListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/secondhand/Loadproduct?" +
                        "access_token=" + token +
                        "&region=" + region +
                        "&money=" + money +
                        "&sate=" + sate +
                        "&keyvalue=" + keyvalue +
                        "&currentpage=" + currentpage)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSecondHandDivision mJsonBeanSecondHandDivision = mGson.fromJson(strJson, JsonBeanSecondHandDivision.class);
                Message message = new Message();
                if (mJsonBeanSecondHandDivision.getRet().equals("0")) {
                    message.arg1 = 6002;
                    message.obj = mJsonBeanSecondHandDivision;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSecondHandDivision.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取二手品详细信息-->6003
    public void getSecondHandDivisionDetails(String token, String pid) {
        try {
            onGetSecondHandDivisionDetailsListener = (NetWorkInterface.OnGetSecondHandDivisionDetailsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/secondhand/Loadinfo?" +
                        "access_token=" + token +
                        "&pid=" + pid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSecondHandDivisionDetails mJsonBeanSecondHandDivisionDetails = mGson.fromJson(strJson, JsonBeanSecondHandDivisionDetails.class);
                Message message = new Message();
                if (mJsonBeanSecondHandDivisionDetails.getRet().equals("0")) {
                    message.arg1 = 6003;
                    message.obj = mJsonBeanSecondHandDivisionDetails;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSecondHandDivisionDetails.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取我的二手品-->6004
    public void getMySecondHandDivision(String token, String currentpage) {
        try {
            onGetMySecondHandDivisionListener = (NetWorkInterface.OnGetMySecondHandDivisionListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/mysecondhand/Loadproduct?" +
                        "access_token=" + token +
                        "&currentpage=" + currentpage
                ).build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMySecondHandDivision mJsonBeanMySecondHandDivision = mGson.fromJson(strJson, JsonBeanMySecondHandDivision.class);
                Message message = new Message();
                if (mJsonBeanMySecondHandDivision.getRet().equals("0")) {
                    message.arg1 = 6004;
                    message.obj = mJsonBeanMySecondHandDivision;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMySecondHandDivision.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取我的二手品详细信息-->6005
    public void getMySecondHandDivisionDetails(String token, String pid) {
        try {
            onGetMySecondHandDivisionDetailsListener = (NetWorkInterface.OnGetMySecondHandDivisionDetailsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/mysecondhand/Loadinfo?" +
                        "access_token=" + token +
                        "&pid=" + pid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMySecondHandDivisionDetails mJsonBeanMySecondHandDivisionDetails = mGson.fromJson(strJson, JsonBeanMySecondHandDivisionDetails.class);
                Message message = new Message();
                if (mJsonBeanMySecondHandDivisionDetails.getRet().equals("0")) {
                    message.arg1 = 6005;
                    message.obj = mJsonBeanMySecondHandDivisionDetails;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMySecondHandDivisionDetails.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //发布我的二手品-->6006
    public void getReleaseSecondHand(String token, String name, String region, String money, String sate, String contacts,
                                     String mobile, String media_ids, String content) {
        try {
            onGetReleaseSecondHandListener = (NetWorkInterface.OnGetReleaseSecondHandListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/mysecondhand/Add?" +
                        "access_token=" + token +
                        "&name=" + name +
                        "&region=" + region +
                        "&money=" + money +
                        "&sate=" + sate +
                        "&contacts=" + contacts +
                        "&mobile=" + mobile +
                        "&media_ids=" + media_ids +
                        "&content=" + content)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();
                Log.e("Tag", "json-->" + strJson);
                JsonBeanReleaseSecondHand mjsonBeanReleaseSecondHand = mGson.fromJson(strJson, JsonBeanReleaseSecondHand.class);
                Message message = new Message();
                if (mjsonBeanReleaseSecondHand.getRet().equals("0")) {
                    message.arg1 = 6006;
                    message.obj = mjsonBeanReleaseSecondHand;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanReleaseSecondHand.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //删除我的二手品信息-->6007
    public void deleteMySecondHand(String token, String pid) {
        try {
            onDeleteMySecondHandDivisionListener = (NetWorkInterface.OnDeleteMySecondHandDivisionListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/mysecondhand/Delete?" +
                        "access_token=" + token +
                        "&pid=" + pid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDeleteMySecondHandData mjsonBeanDeleteMySecondHandData = mGson.fromJson(strJson, JsonBeanDeleteMySecondHandData.class);
                Message message = new Message();
                if (mjsonBeanDeleteMySecondHandData.getRet().equals("0")) {
                    message.arg1 = 6007;
                    message.obj = mjsonBeanDeleteMySecondHandData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanDeleteMySecondHandData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取二手品详情联系人电话-->6008
    public void getSecondHandMobile(String token, String pid) {
        try {
            onGetSecondHandMobileListener = (NetWorkInterface.OnGetSecondHandMobileListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/secondhand/Creditsorder?" +
                        "access_token=" + token +
                        "&pid=" + pid
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanSecondHandMobile mJsonBeanSecondHandMobile = mGson.fromJson(strJson, JsonBeanSecondHandMobile.class);
                Message message = new Message();
                if (mJsonBeanSecondHandMobile.getRet().equals("0")) {
                    message.arg1 = 6008;
                    message.obj = mJsonBeanSecondHandMobile;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanSecondHandMobile.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //用户签到-->7001
    public void getUserSignIn(String token) {
        try {
            onGetUserSignInListener = (NetWorkInterface.OnGetUserSignInListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/signin/clock?access_token=" + token

                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanUserSignIn mjsonBeanUserSignIn = mGson.fromJson(strJson,
                        JsonBeanUserSignIn.class);
                Message message = new Message();
                if (mjsonBeanUserSignIn.getRet().equals("0")) {
                    message.arg1 = 7001;
                    message.obj = mjsonBeanUserSignIn;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanUserSignIn.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //积分商城首页广告 ------7002
    public void getLoadAdverts(String token) {
        try {
            onGetLoadAdvertsListener = (NetWorkInterface.OnGetLoadAdvertsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/credit/Loadadverts?access_token=" + token

                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanLoadAdverts mjsonBeanLoadAdverts = mGson.fromJson(strJson,
                        JsonBeanLoadAdverts.class);
                Message message = new Message();
                if (mjsonBeanLoadAdverts.getRet().equals("0")) {
                    message.arg1 = 7002;
                    message.obj = mjsonBeanLoadAdverts;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanLoadAdverts.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //积分商城排行榜-->7003
    public void getCreditRanking(String token, String type, String currentpage) {
        try {

            onGetLoadCreditRankingListener = (NetWorkInterface.OnGetLoadCreditRankingListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/credit/Loadcreditranking?access_token=" + token
                        + "&type=" + type
                        + "&currentpage=" + currentpage
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanCreditRanking mjsonBeanCreditRanking = mGson.fromJson(strJson,
                        JsonBeanCreditRanking.class);
                Message message = new Message();
                if (mjsonBeanCreditRanking.getRet().equals("0")) {
                    message.arg1 = 7003;
                    message.obj = mjsonBeanCreditRanking;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanCreditRanking.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取积分商城商品数据-->7004
    public void getIntergalShopCpmmodityData(String token, String currentpage) {
        try {

            onGetIntergalShopCommodityListener = (NetWorkInterface.OnGetIntergalShopCommodityListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/credit/Loadcreditproducts?access_token=" + token
                        + "&currentpage=" + currentpage
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanIntergalShopCommodityData mjsonBeanIntergalShopCommodityData = mGson.fromJson(strJson,
                        JsonBeanIntergalShopCommodityData.class);
                Message message = new Message();
                if (mjsonBeanIntergalShopCommodityData.getRet().equals("0")) {
                    message.arg1 = 7004;
                    message.obj = mjsonBeanIntergalShopCommodityData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanIntergalShopCommodityData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //加载单个积分商城产品信息-->7005
    public void getIntergalCommodityInfo(String token, String pid) {
        try {

            onGetIntergalCommodityInfoListener = (NetWorkInterface.OnGetIntergalCommodityInfoListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/credit/Loadinfo?access_token=" + token
                        + "&pid=" + pid

                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanIntergalCommodityInfo mjsonBeanIntergalCommodityInfo = mGson.fromJson(strJson,
                        JsonBeanIntergalCommodityInfo.class);
                Message message = new Message();
                if (mjsonBeanIntergalCommodityInfo.getRet().equals("0")) {
                    message.arg1 = 7005;
                    message.obj = mjsonBeanIntergalCommodityInfo;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanIntergalCommodityInfo.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //兑换积分商品-->7006
    public void exchangeIntergalCommodity(String token, String addressid, String pid) {
        try {
            onExchangeIntegralCommodityListener = (NetWorkInterface.OnExchangeIntegralCommodityListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/creditorders/SubmitOrder?access_token=" + token
                        + "&addressid=" + addressid
                        + "&pid=" + pid
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanExchangeIntegralCommodity mJsonBeanExchangeIntegralCommodity = mGson.fromJson(strJson,
                        JsonBeanExchangeIntegralCommodity.class);
                Message message = new Message();
                if (mJsonBeanExchangeIntegralCommodity.getRet().equals("0")) {
                    message.arg1 = 7006;
                    message.obj = mJsonBeanExchangeIntegralCommodity;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanExchangeIntegralCommodity.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取店铺里优惠券-->8001
    public void getStoreCoupons(String token, String storeid) {
        try {
            onGetStoreCouponsListener = (NetWorkInterface.OnGetStoreCouponsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/coupons/Loadstore?" +
                        "access_token=" + token +
                        "&storeid=" + storeid
                )
                .build();



        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanStoreCoupons mjsonBeanStoreCoupons = mGson.fromJson(strJson, JsonBeanStoreCoupons.class);
                Message message = new Message();
                if (mjsonBeanStoreCoupons.getRet().equals("0")) {
                    message.arg1 = 8001;
                    message.obj = mjsonBeanStoreCoupons;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanStoreCoupons.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //领取订单可用优惠券-->8002
    public void getReceiveCoupons(String token, String couponid) {
        try {
            onGetReceiveCouponsListener = (NetWorkInterface.OnGetReceiveCouponsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS +  "/api/coupons/Receive?" +
                        "access_token=" + token +
                        "&couponid=" + couponid
                )
                .build();




        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanReceiveCoupons mjsonBeanReceiveCoupons = mGson.fromJson(strJson, JsonBeanReceiveCoupons.class);
                Message message = new Message();
                if (mjsonBeanReceiveCoupons.getRet().equals("0")) {
                    message.arg1 = 8002;
                    message.obj = mjsonBeanReceiveCoupons;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanReceiveCoupons.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //我的优惠券-->8003
    public void getMyCouponsData(String token, String State) {
        try {
            onGetMyCouponsListener = (NetWorkInterface.OnGetMyCouponsListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/coupons/Load?access_token=" + token +
                        "&sate=" + State)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyCouponsData mjsonBeanMyCouponsData = mGson.fromJson(strJson, JsonBeanMyCouponsData.class);
                Message message = new Message();
                if (mjsonBeanMyCouponsData.getRet().equals("0")) {
                    message.arg1 = 8003;
                    message.obj = mjsonBeanMyCouponsData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanMyCouponsData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //删除我的优惠券-->8004
    public void deleteCoupon(String token, String couponid) {
        try {
            onGetDeleteCouponListener = (NetWorkInterface.OnGetDeleteCouponListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/coupons/Delete?" +
                        "access_token=" + token +
                        "&couponid=" + couponid)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanDeleteCoupon mjsonBeanDeleteCoupon = mGson.fromJson(strJson, JsonBeanDeleteCoupon.class);
                Message message = new Message();
                if (mjsonBeanDeleteCoupon.getRet().equals("0")) {
                    message.arg1 = 8004;
                    message.obj = mjsonBeanDeleteCoupon;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanDeleteCoupon.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //普通用户未读消息-->9001
    public void getConsumerUncheckMessage(String token) {
        try {
            onGetConsumerUncheckMessageListener = (NetWorkInterface.OnGetConsumerUncheckMessageListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/chatlog/Userisread?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanConsumerUncheckMessage mJsonBeanConsumerUncheckMessage = mGson.fromJson(strJson, JsonBeanConsumerUncheckMessage.class);
                Message message = new Message();
                if (mJsonBeanConsumerUncheckMessage.getRet().equals("0")) {
                    message.arg1 = 9001;
                    message.obj = mJsonBeanConsumerUncheckMessage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanConsumerUncheckMessage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //商户用户未读消息-->9002
    public void getMerchantUncheckMessage(String token) {
        try {
            onGetMerchantUncheckMessageListener = (NetWorkInterface.OnGetMerchantUncheckMessageListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/chatlog/Userisstoreread?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMerchantUncheckMessage mJsonBeanMerchantUncheckMessage = mGson.fromJson(strJson, JsonBeanMerchantUncheckMessage.class);
                Message message = new Message();
                if (mJsonBeanMerchantUncheckMessage.getRet().equals("0")) {
                    message.arg1 = 9002;
                    message.obj = mJsonBeanMerchantUncheckMessage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanMerchantUncheckMessage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //上传图片资源,case-->9003
    public void uploadPic(String token, String cate, String... picAddress) {
        try {
            onGetUploadImageListener = (NetWorkInterface.OnGetUploadImageListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        MultipartBody.Builder mBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (String address : picAddress) {
            File file = new File(address);
            if (file.exists()) {
                Log(file.toString());
                RequestBody mRequestBodyFile = RequestBody.create(MediaType.parse("image/*"), file);
                mBuilder.addFormDataPart("image", file.getName(), mRequestBodyFile);
            } else {
                Log("文件不存在！");
            }
        }

        MultipartBody multipartBody = mBuilder.build();

        Request mRequest = new Request.Builder()
                .url("http://admin.ttsxin.com/media/?" +
                        "access_token=" + token +
                        "&cate=" + cate)
                .post(multipartBody)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log("上传失败-->" + e);
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log("上传成功");
                String strJson = response.body().string();
                Log(strJson);
                JsonBeanUploadImage mjsonBeanUploadImage = mGson.fromJson(strJson, JsonBeanUploadImage.class);
                Message message = new Message();
                if (mjsonBeanUploadImage.getRet().equals("0")) {
                    message.arg1 = 9003;
                    message.obj = mjsonBeanUploadImage;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanUploadImage.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //获取我的收藏信息-->9004
    public void getMyFavorite(String token) {
        try {
            onGetMyFavoriteListener = (NetWorkInterface.OnGetMyFavoriteListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/favorite/Load?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanMyFavoriteData mjsonBeanMyFavoriteData = mGson.fromJson(strJson, JsonBeanMyFavoriteData.class);
                Message message = new Message();
                if (mjsonBeanMyFavoriteData.getRet().equals("0")) {
                    message.arg1 = 9004;
                    message.obj = mjsonBeanMyFavoriteData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanMyFavoriteData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    //编辑店铺收藏状态-->9005
    public void getEditCollect(String storeid, String token) {
        try {
            onGetEditCollectListener = (NetWorkInterface.OnGetEditCollectListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/favorite/EditBystoreorid?storeorid=" + storeid
                        + "&access_token=" + token
                )
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanEditCollect mjsonBeanEditCollect = mGson.fromJson(strJson,
                        JsonBeanEditCollect.class);
                Message message = new Message();
                if (mjsonBeanEditCollect.getRet().equals("0")) {
                    message.arg1 = 9005;
                    message.obj = mjsonBeanEditCollect;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanEditCollect.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    private class NetWorkOperateHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1) {
                case 0: {
                    //请求失败
                    try {
                        listener.onFailure(strMessage);
                    } catch (Exception exception) {
                        Log(exception + "");
                    }
                    break;
                }
                case 1001: {
                    //获取初始化token
                    onGetInitialTokenListener.onGetToken((JsonBeanGetToken) msg.obj);
                    break;
                }
                case 1002: {
                    //获取登陆后的token
                    onGetNewTokenListener.onGetNewToken((JsonBeanGetToken) msg.obj);
                    break;
                }
                case 1003: {
                    //判断是否登录
                    onGetLoginStateListener.onGetLoginState((JsonBeanLoginState) msg.obj);
                    break;
                }
                case 1004: {
                    //用户注册获取手机验证码
                    onGetUserRegisterCodeListener.onGetUserRegisterCode((JsonBeanRegisterCode) msg.obj);
                    break;
                }
                case 1005: {
                    //用户注册
                    onGetUserRegisterListener.onGetUserRegister((JsonBeanUserRegister) msg.obj);
                    break;
                }
                case 1006: {
                    //忘记密码验证码
                    onGetForgetPasswordCodeListener.onGetForGetPasswordCode((JsonBeanForgetPasswordCode) msg.obj);
                    break;
                }
                case 1007: {
                    //忘记密码
                    onGetForgetPasswordListener.onGetForgetPassword((JsonBeanForgetPassword) msg.obj);
                    break;
                }
                case 1008: {
                    //修改个人密码
                    onGetUpdatePassWordListener.onUpdatePassWord((JsonBeanUpdatePassWord) msg.obj);
                    break;
                }
                case 1009: {
                    //获取用户信息
                    onGetUserInformationListener.onGetUserInformationData((JsonBeanUserInformationData) msg.obj);
                    break;
                }
                case 1010: {
                    //获取经纬度信息的token
                    onGetNewTokenListener.onGetNewToken((JsonBeanGetToken) msg.obj);
                    break;
                }
                case 1011: {
                    //获取区域信息
                    onGetRegionalDataListener.onGetRegionalData((JsonBeanRegionalData) msg.obj);
                    break;
                }
                case 1012: {
                    //修改用户名
                    onModifyUserNameListener.onModifyUserName((JsonBeanModifyUserName) msg.obj);
                    break;
                }
                case 1013: {
                    //修改用户头像
                    onModifyUserPhotoListener.onModifyUserPhoto((JsonBeanModifyUserPhoto) msg.obj);
                    break;
                }
                case 1014: {
                    //写入定位区域
                    onGetNewTokenListener.onGetNewToken((JsonBeanGetToken) msg.obj);
                    break;
                }
                case 1015: {
                    //获取版本信息
                    onGetCurrentVersionListener.onGetCurrentVersion((JsonBeanCurrentVersion) msg.obj);
                    break;
                }
                case 2001: {
                    //我的订单
                    onGetMyAllOrderDataListener.onGetMyAllOrderData((JsonBeanMyAllOrder) msg.obj);
                    break;
                }
                case 2002: {
                    //订单详情
                    onGetOrderDetailsDataListener.onGetOrderDetailsData((JsonBeanOrderDetailsData) msg.obj);
                    break;
                }
                case 2003: {
                    //添加订单
                    onAddOrderListener.onAddOrder((JsonBeanAddOrder) msg.obj);
                    break;
                }
                case 2004: {
                    //获取支付宝字窜
                    onGetAliPayTextListener.onGetAliPayText((JsonBeanAliPayText) msg.obj);
                    break;
                }
                case 2005: {
                    //评价页面
                    onGetGoEvaluateListner.onGetGoEvaluate((JsonBeanGoEvaluate) msg.obj);
                    break;
                }
                case 2006: {
                    //取消订单
                    onGetCancelOrderListener.onGetCancelOrder((JsonBeanCancelOrder) msg.obj);
                    break;
                }
                case 2007: {
                    //申请退款
                    onGetApplyRefundListener.onGetApplyRefund((JsonBeanApplyRefund) msg.obj);
                    break;
                }
                case 2008: {
                    //判断是否显示
                    onGetShowListener.onGetShow((JsonBeanGetShow) msg.obj);
                    break;
                }
                case 2009: {
                    //获取商户订单
                    onGetMerChantOrderDataListener.onGetMerChantOrderData((JsonBeanMerchantOrder) msg.obj);
                    break;
                }
                case 2010: {
                    //获取商户订单详情
                    onGetMechantOrderDetailsDataListener.onGetMechantOrderDetailsData((JsonBeanMerchantOrderDetailsData) msg.obj);
                    break;
                }
                case 2011: {
                    //删除我的订单
                    onDeleteMyOrderlistener.onDeleteMyOrder((JsonBeanDeleteMyOrder) msg.obj);
                    break;
                }
                case 2012: {
                    //获取去评论商品数据
                    onGetEvaluateProductListener.onGetEvaluateProduct((JsonBeanEvaluateProduct) msg.obj);
                    break;
                }
                case 2013: {
                    //获取商品详情更多评论
                    onGetMoreCommentListener.onGetMoreComment((JsonBeanMoreComment) msg.obj);
                    break;
                }
                case 2014: {
                    //获取我的评论列表
                    onGetMyCommentListener.onGetMyComment((JsonBeanMyComment) msg.obj);
                    break;
                }
                case 2015: {
                    //提交我的评价
                    onSubmitEvaluationListener.onSubmitEvaluation((JsonBeanSubmitEvaluation) msg.obj);
                    break;
                }
                case 2016: {
                    //获取配送时间
                    onGetDeliveryTimeListener.onGetDeliveryTime((JsonBeanDeliveryTime) msg.obj);
                    break;
                }
                case 2017: {
                    //商户订单编辑接口
                    onGetMerchantOrderEditListener.onGetMerchantOrderEdit((JsonBeanMerchantOrderEdit) msg.obj);
                    break;
                }
                case 2018: {
                    //申请退款原因
                    onGetRefundReasonListener.onGetRefundReason((JsonBeanRefundReason) msg.obj);
                    break;
                }
                case 2019: {
                    //优惠券
                    onGetCouponListener.onGetCoupon((SettlemCoupons) msg.obj);
                    break;
                }
                case 3001: {
                    //加载配送的地址
                    onLoadAddressListener.onLoadAddress((JsonBeanLoadAddress) msg.obj);
                    break;
                }
                case 3002: {
                    //获取附近的地址
                    onGetNearbyAddressListener.onGetNearbyAddress((JsonBeanNearbyAddress) msg.obj);
                    break;
                }
                case 3003: {
                    //添加收货地址
                    onAddAddressListener.onAddAddress((JsonBeanAddAddress) msg.obj);
                    break;
                }
                case 3004: {
                    //编辑收货地址
                    onEditAddressListener.onEditAddress((JsonBeanEditAddress) msg.obj);
                    break;
                }
                case 3005: {
                    //删除收货地址
                    onDeleteAddressListener.onDeleteAddress((JsonBeanDeleteAddress) msg.obj);
                    break;
                }
                case 4001: {
                    //获取首页数据
                    onGetHomaPageDataListener.onGetData((JsonBeanHomePage) msg.obj);
                    break;
                }
                case 4014: {

                    //获取首页数据
                    onGetHomaPageAdvertsListOneListener.onGetDataOne((JsonBeanAdverts) msg.obj);
                    break;
                }
                case 4015: {

                    //获取首页数据
                    onGetHomaPageAdvertsListTwoListener.onGetDataTwo((JsonBeanAdverts) msg.obj);
                    break;
                }
                case 4016: {

                    //获取首页数据
                    onGetHomaPageAdvertsListThreeListener.onGetDataThree((JsonBeanAdverts) msg.obj);
                    break;
                }
                case 4017: {

                    //获取首页数据
                    onGetHomaPageAdvertsListFourListener.onGetDataFour((JsonBeanAdverts) msg.obj);
                    break;
                }
                case 4020: {

                    //获取首页数据
                    onGetHomaPageAdvertsListFiveListener.onGetDataFive((JsonBeanAdverts) msg.obj);
                    break;
                }
                case 4018: {

                    //获取首页数据

                    OnGetHomaLoadTypeListener.onGetDataLoadType((List<HorizontaiListviewData>) msg.obj);
                    break;
                }
                case 4021: {

                    //获取首页数据o

                    onGetLoadproductsListListener.onGetDataLoadproducts((HomaLoadproductsData) msg.obj);
                    break;
                }
                case 4019: {

                    //获取首页数据

                    onGetHomaLoadTypeListListener.onGetDataList((HorizontaiListviewListData) msg.obj);
                    break;
                }
                case 4002: {
                    //获取分类信息左边数据
                    onGetClassificationLeftDataListener.onGetClassificationLeftData((JsonBeanClassificationLeft) msg.obj);
                    break;
                }
                case 4003: {
                    //获取分类信息右边数据
                    onGetClassificationRightDataListener.onGetClassificationRightData((JsonBeanClassificationRightData) msg.obj);
                    break;
                }
                case 4004: {
                    //获取店铺数据
                    onGetStoreDataListener.onGetStoreData((JsonBeanStoreData) msg.obj);
                    break;
                }
                case 4005: {
                    //获取商店详情数据
                    onGetStoreDetailsDataListener.onGetStoreDetailsData((JsonBeanStoreDetailsData) msg.obj);
                    break;
                }
                case 4022: {
                    //获取商店详情数据
                    onGetStoreDetails2DataListener.onGetStoreDetails2Data((StoresinfoData) msg.obj);
                    break;
                }
                case 4023: {
                    //获取商店基本详情数据
                    onGetStoreDetails3DataListener.onGetStoreDetails3Data((StoresBaseInfoData) msg.obj);
                    break;
                }
                case 4006: {
                    //获取商店详情内商品数据
                    onGetStoreDetailsCommodityDataListener.onGetStoreDetailsCommodityData((JsonBeanStoreDetailsCommodityData) msg.obj);
                    break;
                }
                case 4007: {
                    //获取商品详情数据
                    onGetCommodityDetailsDataListener.onGetCommodityDetailsData((JsonBeanCommodityDetailsData) msg.obj);
                    break;
                }
                case 4008: {
                    //获取商品详情的评论
                    onGetCommodityDetailsCommentListener.onGetCommodityDetailsComment((JsonBeanCommodityDetailsComment) msg.obj);
                    break;
                }
                case 4009: {
                    //获取自营店铺数据
                    onGetSelfStoreDataListener.onGetSelfStoreData((JsonBeanSelfStoreData) msg.obj);
                    break;
                }
                case 4010: {
                    //获取自营店更多页面数据
                    onGetSelfStoreMoreDataListener.onGetSelfStoreMoreData((JsonBeanSelfStoreMoreData) msg.obj);
                    break;
                }
                case 4011: {
                    //获取搜索结果数据，可由CateNumber，也可由SearchKey
                    onGetSearchDataListener.onGetSearchData((JsonBeanSearhResultData) msg.obj);
                    break;
                }
                case 4013: {
                    //获取今日特价
                    onGetDailySaleListener.onGetDailySale((JsonBeanDailySale) msg.obj);
                    break;
                }
                case 5001: {
                    //获取便民服务首页广告
                    onGetConvenienceServicesBannerListener.onGetConvenienceServicesBanner((JsonBeanConvenienceServicesBanner) msg.obj);
                    break;
                }
                case 5002: {
                    //获取便民服务资讯类列表
                    onGetConvenienceServicesListener.onGetConvenienceServices((JsonBeanConvenienceServices) msg.obj);
                    break;
                }
                case 5003: {
                    //获取便民服务详细信息
                    onGetConvenienceServicesDetailsListener.onGetConvenienceServicesDetails((JsonBeanConvenienceServicesDetails) msg.obj);
                    break;
                }
                case 5004: {
                    //获取我的便民服务
                    onGetMyConvenienceServicesListener.onGetMyConvenienceServices((JsonBeanMyConvenienceServices) msg.obj);
                    break;
                }
                case 5005: {
                    //获取我的便民服务详情
                    onGetMyServicesDetailsListener.onGetMyServicesDetails((JsonBeanMyServicesDetails) msg.obj);
                    break;
                }
                case 5006: {
                    //发布我的便民服务
                    onGetReleaseServiceListener.onGetReleaseService((JsonBeanReleaseService) msg.obj);
                    break;
                }
                case 5007: {
                    //删除我的便民服务
                    onDeleteMyConvenienceServicesListener.onDeleteMyConvenienceServices((JsonBeanDeleteMyConvenienceServices) msg.obj);
                    break;
                }
                case 5008: {
                    //获取我的便民服务类别
                    onGetMyServiceCategoryListener.onGetMyServiceCategory((JsonBeanMyServiceCategory) msg.obj);
                    break;
                }
                case 5009: {
                    //获取我的便民服务类别
                    onGetServiceMobileListener.onGetServiceMobile((JsonBeanServiceMobile) msg.obj);
                    break;
                }
                case 6001: {
                    //获取二手品首页广告
                    onGetSecondHandDivisionBannerListener.onGetSecondHandDivisionBanner((JsonBeanSecondHandDivisionBanner) msg.obj);
                    break;
                }
                case 6002: {
                    //获取二手品产品数据
                    onGetSecondHandDivisionListener.onGetSecondHandDivision((JsonBeanSecondHandDivision) msg.obj);
                    break;
                }
                case 6003: {
                    //获取二手品详细信息
                    onGetSecondHandDivisionDetailsListener.onGetSecondHandDivisionDetails((JsonBeanSecondHandDivisionDetails) msg.obj);
                    break;
                }
                case 6004: {
                    //获取我的二手品信息
                    onGetMySecondHandDivisionListener.onGetMySecondHandDivision((JsonBeanMySecondHandDivision) msg.obj);
                    break;
                }
                case 6005: {
                    //获取我的二手品详细信息
                    onGetMySecondHandDivisionDetailsListener.onGetMySecondHandDivisionDetails((JsonBeanMySecondHandDivisionDetails) msg.obj);
                    break;
                }
                case 6006: {
                    //发布二手品
                    onGetReleaseSecondHandListener.onGetReleaseSecondHand((JsonBeanReleaseSecondHand) msg.obj);
                    break;
                }
                case 6007: {
                    //删除我的二手品信息
                    onDeleteMySecondHandDivisionListener.onDeleteMySecondHand((JsonBeanDeleteMySecondHandData) msg.obj);
                    break;
                }
                case 6008: {
                    onGetSecondHandMobileListener.onGetSecondHandMobile((JsonBeanSecondHandMobile) msg.obj);
                    break;
                }
                case 7001: {
                    //用户签到
                    onGetUserSignInListener.onGetUserSignIn((JsonBeanUserSignIn) msg.obj);
                    break;
                }
                case 7002: {
                    //积分商城广告
                    onGetLoadAdvertsListener.onGetLoadAdverts((JsonBeanLoadAdverts) msg.obj);
                    break;
                }
                case 7003: {
                    //积分商城排行榜
                    onGetLoadCreditRankingListener.onGetLoadCreditRanking((JsonBeanCreditRanking) msg.obj);
                    break;
                }
                case 7004: {
                    //积分商城商品数据
                    onGetIntergalShopCommodityListener.onGetIntergalShopCommodity((JsonBeanIntergalShopCommodityData) msg.obj);
                    break;
                }
                case 7005: {
                    //获取单个积分产品信息
                    onGetIntergalCommodityInfoListener.onGetIntergalCommodityInfo((JsonBeanIntergalCommodityInfo) msg.obj);
                    break;
                }
                case 7006: {
                    //兑换积分商品
                    onExchangeIntegralCommodityListener.onExchangeIntegralCommodity((JsonBeanExchangeIntegralCommodity) msg.obj);
                    break;
                }
                case 8001: {
                    //获取店铺优惠券
                    onGetStoreCouponsListener.onGetStoreCoupons((JsonBeanStoreCoupons) msg.obj);
                    break;
                }
                case 8002: {
                    //领取优惠券
                    onGetReceiveCouponsListener.onGetReceiveCoupons((JsonBeanReceiveCoupons) msg.obj);
                    break;
                }
                case 8003: {
                    //获取我的优惠券数据
                    onGetMyCouponsListener.onGetMyCouponsData((JsonBeanMyCouponsData) msg.obj);
                    break;
                }
                case 8004: {
                    //删除优惠券
                    onGetDeleteCouponListener.onGetDeleteCoupon((JsonBeanDeleteCoupon) msg.obj);
                    break;
                }
                case 9001: {
                    //普通用户未读信息
                    onGetConsumerUncheckMessageListener.onGetConsumerUncheckMessage((JsonBeanConsumerUncheckMessage) msg.obj);
                    break;
                }
                case 9002: {
                    //商户用户未读信息
                    onGetMerchantUncheckMessageListener.onGetMerchantUncheckMessage((JsonBeanMerchantUncheckMessage) msg.obj);
                    break;
                }
                case 9003: {
                    //上传本地图片
                    onGetUploadImageListener.onGetUploadImage((JsonBeanUploadImage) msg.obj);
                    break;
                }
                case 9004: {
                    //获取我的收藏信息
                    onGetMyFavoriteListener.onGetMyFavoriteData((JsonBeanMyFavoriteData) msg.obj);
                    break;
                }
                case 9005: {
                    //编辑店铺收藏状态
                    onGetEditCollectListener.onGetEditCollect((JsonBeanEditCollect) msg.obj);
                    break;
                }
                default: {
                    //default
                    Log("NetWorkOperateHandler.default" + "\tcase-->" + msg.arg1);
                    break;
                }
            }
        }
    }

    /*//MD5加密
    private String getMD5(String password) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        Log.e("Tag", "MD5-->" + hex.toString());
        return hex.toString();
    }

    //退出登录-->1006
    public void getLoginOut(String token) {
        try {
            onLoginOutListener = (NetWorkInterface.OnLoginOutListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }
        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/LoginOut?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanLoginOut mjsonBeanLoginOut = mGson.fromJson(strJson, JsonBeanLoginOut.class);
                Message message = new Message();
                if (mjsonBeanLoginOut.getRet().equals("0")) {
                    message.arg1 = 1006;
                    message.obj = mjsonBeanLoginOut;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanLoginOut.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    *//* //获取分类数据，对应case-->4001
    public void getClassficationData(String token) {
        try {
            onGetClassificationDataListener = (NetWorkInterface.OnGetClassificationDataListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/categories/Load?access_token=" + token)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanClassificationData mJsonBeanClassificationData = mGson.fromJson(strJson, JsonBeanClassificationData.class);
                Message message = new Message();
                if (mJsonBeanClassificationData.getRet().equals("0")) {
                    message.arg1 = 4001;
                    message.obj = mJsonBeanClassificationData;
                    handler.sendMessage(message);
                } else {
                    strMessage = mJsonBeanClassificationData.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }
    //修改性别-->1008
    public void getUpdateSex(String token, String sex) {
        try {
            onGetUpdateUserSexListener = (NetWorkInterface.OnGetUpdateUserSexListener) listener;
        } catch (Exception e) {
            Log(e + "");
        }

        mRequest = new Request.Builder()
                .url(ADDRESS + "/api/auth/UpdateUserSex?access_token" + token + "&sex=" + sex)
                .build();

        Log(mRequest.url().toString());

        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = new Message();
                message.arg1 = 0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strJson = response.body().string();

                Log.e("Tag", "json-->" + strJson);
                JsonBeanUpdateUserSex mjsonBeanUpdateUserSex = mGson.fromJson(strJson, JsonBeanUpdateUserSex.class);
                Message message = new Message();
                if (mjsonBeanUpdateUserSex.getRet().equals("0")) {
                    message.arg1 = 1008;
                    message.obj = mjsonBeanUpdateUserSex;
                    handler.sendMessage(message);
                } else {
                    strMessage = mjsonBeanUpdateUserSex.getMsg();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }


    */
}
