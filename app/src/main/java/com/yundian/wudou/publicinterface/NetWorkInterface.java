package com.yundian.wudou.publicinterface;

import com.yundian.wudou.data.HomaLoadproductsData;
import com.yundian.wudou.data.HorizontaiListviewData;
import com.yundian.wudou.data.HorizontaiListviewListData;
import com.yundian.wudou.data.SettlemCoupons;
import com.yundian.wudou.data.StoresBaseInfoData;
import com.yundian.wudou.data.StoresinfoData;
import com.yundian.wudou.network.JsonBeanAddAddress;
import com.yundian.wudou.network.JsonBeanAddOrder;
import com.yundian.wudou.network.JsonBeanAdverts;
import com.yundian.wudou.network.JsonBeanAliPayText;
import com.yundian.wudou.network.JsonBeanApplyRefund;
import com.yundian.wudou.network.JsonBeanCancelOrder;
import com.yundian.wudou.network.JsonBeanClassificationData;
import com.yundian.wudou.network.JsonBeanClassificationLeft;
import com.yundian.wudou.network.JsonBeanClassificationRightData;
import com.yundian.wudou.network.JsonBeanCommodityDetailsComment;
import com.yundian.wudou.network.JsonBeanCommodityDetailsData;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.JsonBeanConvenienceServices;
import com.yundian.wudou.network.JsonBeanConvenienceServicesBanner;
import com.yundian.wudou.network.JsonBeanConvenienceServicesDetails;
import com.yundian.wudou.network.JsonBeanCreditRanking;
import com.yundian.wudou.network.JsonBeanCurrentVersion;
import com.yundian.wudou.network.JsonBeanDailySale;
import com.yundian.wudou.network.JsonBeanDeleteAddress;
import com.yundian.wudou.network.JsonBeanDeleteCoupon;
import com.yundian.wudou.network.JsonBeanDeleteMyConvenienceServices;
import com.yundian.wudou.network.JsonBeanDeleteMyOrder;
import com.yundian.wudou.network.JsonBeanDeleteMySecondHandData;
import com.yundian.wudou.network.JsonBeanDeliveryTime;
import com.yundian.wudou.network.JsonBeanEditAddress;
import com.yundian.wudou.network.JsonBeanEditCollect;
import com.yundian.wudou.network.JsonBeanEvaluateProduct;
import com.yundian.wudou.network.JsonBeanExchangeIntegralCommodity;
import com.yundian.wudou.network.JsonBeanForgetPassword;
import com.yundian.wudou.network.JsonBeanForgetPasswordCode;
import com.yundian.wudou.network.JsonBeanGetShow;
import com.yundian.wudou.network.JsonBeanGetToken;
import com.yundian.wudou.network.JsonBeanGoEvaluate;
import com.yundian.wudou.network.JsonBeanHomePage;
import com.yundian.wudou.network.JsonBeanIntergalCommodityInfo;
import com.yundian.wudou.network.JsonBeanIntergalShopCommodityData;
import com.yundian.wudou.network.JsonBeanLoadAddress;
import com.yundian.wudou.network.JsonBeanLoadAdverts;
import com.yundian.wudou.network.JsonBeanLoginOut;
import com.yundian.wudou.network.JsonBeanLoginState;
import com.yundian.wudou.network.JsonBeanMerchantOrder;
import com.yundian.wudou.network.JsonBeanMerchantOrderDetailsData;
import com.yundian.wudou.network.JsonBeanMerchantOrderEdit;
import com.yundian.wudou.network.JsonBeanMerchantUncheckMessage;
import com.yundian.wudou.network.JsonBeanModifyUserName;
import com.yundian.wudou.network.JsonBeanModifyUserPhoto;
import com.yundian.wudou.network.JsonBeanMoreComment;
import com.yundian.wudou.network.JsonBeanMyAllOrder;
import com.yundian.wudou.network.JsonBeanMyComment;
import com.yundian.wudou.network.JsonBeanMyConvenienceServices;
import com.yundian.wudou.network.JsonBeanMyCouponsData;
import com.yundian.wudou.network.JsonBeanMyFavoriteData;
import com.yundian.wudou.network.JsonBeanMySecondHandDivision;
import com.yundian.wudou.network.JsonBeanMySecondHandDivisionDetails;
import com.yundian.wudou.network.JsonBeanMyServiceCategory;
import com.yundian.wudou.network.JsonBeanMyServicesDetails;
import com.yundian.wudou.network.JsonBeanNearbyAddress;
import com.yundian.wudou.network.JsonBeanOrderDetailsData;
import com.yundian.wudou.network.JsonBeanReceiveCoupons;
import com.yundian.wudou.network.JsonBeanRefundReason;
import com.yundian.wudou.network.JsonBeanRegionalData;
import com.yundian.wudou.network.JsonBeanRegisterCode;
import com.yundian.wudou.network.JsonBeanReleaseSecondHand;
import com.yundian.wudou.network.JsonBeanReleaseService;
import com.yundian.wudou.network.JsonBeanSearhResultData;
import com.yundian.wudou.network.JsonBeanSecondHandDivision;
import com.yundian.wudou.network.JsonBeanSecondHandDivisionBanner;
import com.yundian.wudou.network.JsonBeanSecondHandDivisionDetails;
import com.yundian.wudou.network.JsonBeanSecondHandMobile;
import com.yundian.wudou.network.JsonBeanSelfStoreData;
import com.yundian.wudou.network.JsonBeanSelfStoreMoreData;
import com.yundian.wudou.network.JsonBeanServiceMobile;
import com.yundian.wudou.network.JsonBeanShipAddressData;
import com.yundian.wudou.network.JsonBeanStoreCoupons;
import com.yundian.wudou.network.JsonBeanStoreData;
import com.yundian.wudou.network.JsonBeanStoreDetailsCommodityData;
import com.yundian.wudou.network.JsonBeanStoreDetailsData;
import com.yundian.wudou.network.JsonBeanSubmitEvaluation;
import com.yundian.wudou.network.JsonBeanUpdatePassWord;
import com.yundian.wudou.network.JsonBeanUpdateUserSex;
import com.yundian.wudou.network.JsonBeanUploadImage;
import com.yundian.wudou.network.JsonBeanUserInformationData;
import com.yundian.wudou.network.JsonBeanUserRegister;
import com.yundian.wudou.network.JsonBeanUserSignIn;

import java.util.List;

/**
 * Created by cookie on 2016/7/21.
 */
public class NetWorkInterface {

    /***
     * 接口，监听器
     ***/
    //初始化token
    public interface OnGetInitialTokenListener extends Listener {
        void onGetToken(JsonBeanGetToken jsonBeanGetToken);
    }

    //获取改变后的token
    public interface OnGetNewTokenListener extends Listener {
        void onGetNewToken(JsonBeanGetToken jsonBeanGetToken);
    }

    //获取首页数据
    public interface OnGetHomaPageDataListener extends Listener {
        void onGetData(JsonBeanHomePage jsonBeanHomePage);
    }

    //获取首页第一个轮播图下方数据数据
    public interface OnGetHomaPageAdvertsListOneListener extends Listener {
        void onGetDataOne(JsonBeanAdverts jsonBeanHomePage);
    }

    //获取首页第二个轮播图数据
    public interface OnGetHomaPageAdvertsListTwoListener extends Listener {
        void onGetDataTwo(JsonBeanAdverts jsonBeanHomePage);
    }

    //获取首页第二个轮播图数据
    public interface OnGetHomaPageAdvertsListThreeListener extends Listener {
        void onGetDataThree(JsonBeanAdverts jsonBeanHomePage);
    }

    //获取首页第3个轮播图数据
    public interface OnGetHomaPageAdvertsListFourListener extends Listener {
        void onGetDataFour(JsonBeanAdverts jsonBeanHomePage);
    }

    //获取首页精品推荐轮播图数据
    public interface OnGetHomaPageAdvertsListFiveListener extends Listener {
        void onGetDataFive(JsonBeanAdverts jsonBeanHomePage);
    }

    //获取首页附近店铺列表数据
    public interface OnGetHomaLoadTypeListListener extends Listener {
        void onGetDataList(HorizontaiListviewListData jsonBeanHomePage);
    }

    //获取首页附近店铺列表数据
    public interface OnGetLoadproductsListListener extends Listener {
        void onGetDataLoadproducts(HomaLoadproductsData jsonBeanHomePage);
    }

    //获取首页附近店铺菜单数据
    public interface OnGetHomaLoadTypeListener extends Listener {
        void onGetDataLoadType(List<HorizontaiListviewData> jsonBeanHomePage);
    }

    //获取店铺信息
    public interface OnGetStoreDataListener extends Listener {
        void onGetStoreData(JsonBeanStoreData jsonBeanStoreData);
    }

    //获取商品详情
    public interface OnGetCommodityDetailsDataListener extends Listener {
        void onGetCommodityDetailsData(JsonBeanCommodityDetailsData jsonBeanCommodityDetailsData);
    }

    //获取商品详情里的评价
    public interface OnGetCommodityDetailsCommentListener extends Listener {
        void onGetCommodityDetailsComment(JsonBeanCommodityDetailsComment jsonBeanCommodityDetailsComment);
    }

    //获取店铺详情
    public interface OnGetStoreDetailsDataListener extends Listener {
        void onGetStoreDetailsData(JsonBeanStoreDetailsData jsonBeanStoreDetailsData);
    }

    //获取店铺详情
    public interface OnGetStoreDetails2DataListener extends Listener {
        void onGetStoreDetails2Data(StoresinfoData jsonBeanStoreDetailsData);
    }
    //获取店铺详情
    public interface OnGetStoreDetails3DataListener extends Listener {
        void onGetStoreDetails3Data(StoresBaseInfoData jsonBeanStoreDetailsData);
    }

    public interface OnGetStoreDetailsCommodityDataListener extends Listener {
        void onGetStoreDetailsCommodityData(JsonBeanStoreDetailsCommodityData jsonBeanStoreDetailsCommodityData);
    }

    public interface OnGetSearchDataListener extends Listener {
        void onGetSearchData(JsonBeanSearhResultData jsonBeanSearhResultData);
    }

    //加载配送地址
    public interface OnLoadAddressListener extends Listener {
        void onLoadAddress(JsonBeanLoadAddress jsonBeanLoadAddress);
    }

    //添加配送地址
    public interface OnAddAddressListener extends Listener {
        void onAddAddress(JsonBeanAddAddress jsonBeanAddAddress);
    }

    //编辑配送地址
    public interface OnEditAddressListener extends Listener {
        void onEditAddress(JsonBeanEditAddress jsonBeanEditAddress);
    }

    //删除配送地址
    public interface OnDeleteAddressListener extends Listener {
        void onDeleteAddress(JsonBeanDeleteAddress jsonBeanDeleteAddress);
    }

    // 判断是否登录
    public interface OnGetLoginStateListener extends Listener {
        void onGetLoginState(JsonBeanLoginState jsonBeanLoginState);
    }

    //得到用户信息
    public interface OnGetUserInformationListener extends Listener {
        void onGetUserInformationData(JsonBeanUserInformationData jsonBeanUserInformationData);
    }

    //修改登录密码
    public interface OnGetUpdatePassWordListener extends Listener {
        void onUpdatePassWord(JsonBeanUpdatePassWord jsonBeanUpdatePassWord);
    }

    //获取收藏列表
    public interface OnGetMyFavoriteListener extends Listener {
        void onGetMyFavoriteData(JsonBeanMyFavoriteData jsonBeanMyFavoriteData);
    }

    //获取我的优惠券
    public interface OnGetMyCouponsListener extends Listener {
        void onGetMyCouponsData(JsonBeanMyCouponsData jsonBeanMyCouponsData);
    }

    // 删除优惠券
    public interface OnGetDeleteCouponListener extends Listener {
        void onGetDeleteCoupon(JsonBeanDeleteCoupon jsonBeanDeleteCoupon);
    }

    //获取分类左边数据
    public interface OnGetClassificationLeftDataListener extends Listener {
        void onGetClassificationLeftData(JsonBeanClassificationLeft jsonBeanClassificationLeft);
    }

    //获取分类右边数据
    public interface OnGetClassificationRightDataListener extends Listener {
        void onGetClassificationRightData(JsonBeanClassificationRightData jsonBeanClassificationRightData);
    }

    //用户注册获取验证码
    public interface OnGetUserRegistrCodeListener extends Listener {
        void onGetUserRegisterCode(JsonBeanRegisterCode jsonBeanRegisterCode);

    }

    //用户注册接口
    public interface OnGetUserRegisterListener extends Listener {
        void onGetUserRegister(JsonBeanUserRegister jsonBeanUserRegister);
    }

    //自营店铺接口
    public interface OnGetSelfStoreDataListener extends Listener {
        void onGetSelfStoreData(JsonBeanSelfStoreData jsonBeanSelfStoreData);
    }

    //自营店更多页面接口
    public interface OnGetSelfStoreMoreDataListener extends Listener {
        void onGetSelfStoreMoreData(JsonBeanSelfStoreMoreData jsonBeanSelfStoreMoreData);
    }

    //我的订单接口
    public interface OnGetMyAllOrderDataListener extends Listener {
        void onGetMyAllOrderData(JsonBeanMyAllOrder jsonBeanMyAllOrder);
    }

    //商户订单接口
    public interface OnGetMerChantOrderDataListener extends Listener {
        void onGetMerChantOrderData(JsonBeanMerchantOrder jsonBeanMerchantOrder);
    }

    //商户订单详情接口
    public interface OnGetMechantOrderDetailsDataListener extends Listener {
        void onGetMechantOrderDetailsData(JsonBeanMerchantOrderDetailsData jsonBeanMerchantOrderDetailsData);
    }

    //订单详情接口
    public interface OnGetOrderDetailsDataListener extends Listener {
        void onGetOrderDetailsData(JsonBeanOrderDetailsData jsonBeanOrderDetailsData);
    }

    //添加订单
    public interface OnAddOrderListener extends Listener {
        void onAddOrder(JsonBeanAddOrder jsonBeanAddOrder);
    }

    //获取便民服务首页广告
    public interface OnGetConvenienceServicesBannerListener extends Listener {
        void onGetConvenienceServicesBanner(JsonBeanConvenienceServicesBanner jsonBeanConvenienceServicesBanner);
    }

    //获取便民服务资讯类列表
    public interface OnGetConvenienceServicesListener extends Listener {
        void onGetConvenienceServices(JsonBeanConvenienceServices jsonBeanConvenienceServices);
    }

    //获取便民服务详细信息
    public interface OnGetConvenienceServicesDetailsListener extends Listener {
        void onGetConvenienceServicesDetails(JsonBeanConvenienceServicesDetails jsonBeanConvenienceServicesDetails);
    }

    //获取二手品首页广告
    public interface OnGetSecondHandDivisionBannerListener extends Listener {
        void onGetSecondHandDivisionBanner(JsonBeanSecondHandDivisionBanner jsonBeanSecondHandDivisionBanner);
    }

    //获取二手品首页产品数据
    public interface OnGetSecondHandDivisionListener extends Listener {
        void onGetSecondHandDivision(JsonBeanSecondHandDivision jsonBeanSecondHandDivision);
    }

    //获取二手品详细信息
    public interface OnGetSecondHandDivisionDetailsListener extends Listener {
        void onGetSecondHandDivisionDetails(JsonBeanSecondHandDivisionDetails jsonBeanSecondHandDivisionDetails);
    }

    //获取我的二手品信息
    public interface OnGetMySecondHandDivisionListener extends Listener {
        void onGetMySecondHandDivision(JsonBeanMySecondHandDivision jsonBeanMySecondHandDivision);
    }

    //获取我的二手品详细信息
    public interface OnGetMySecondHandDivisionDetailsListener extends Listener {
        void onGetMySecondHandDivisionDetails(JsonBeanMySecondHandDivisionDetails jsonBeanMySecondHandDivision);
    }

    //删除我的二手品
    public interface OnDeleteMySecondHandDivisionListener extends Listener {
        void onDeleteMySecondHand(JsonBeanDeleteMySecondHandData jsonBeanDeleteMySecondHandData);
    }

    //发布我的二手品
    public interface OnGetReleaseSecondHandListener extends Listener {
        void onGetReleaseSecondHand(JsonBeanReleaseSecondHand jsonBeanReleaseSecondHand);
    }

    //加载我的便民服务列表
    public interface OnGetMyConvenienceServicesListener extends Listener {
        void onGetMyConvenienceServices(JsonBeanMyConvenienceServices jsonBeanMyConvenienceServices);

    }

    //删除我的便民服务
    public interface OnDeleteMyConvenienceServicesListener extends Listener {
        void onDeleteMyConvenienceServices(JsonBeanDeleteMyConvenienceServices jsonBeanDeleteMyConvenienceServices);
    }

    //发布我的便民服务
    public interface OnGetReleaseServiceListener extends Listener {
        void onGetReleaseService(JsonBeanReleaseService jsonBeanReleaseService);
    }

    //加载我的便民服务详情
    public interface OnGetMyServicesDetailsListener extends Listener {
        void onGetMyServicesDetails(JsonBeanMyServicesDetails jsonBeanMyServicesDetails);
    }

    //用户签到
    public interface OnGetUserSignInListener extends Listener {
        void onGetUserSignIn(JsonBeanUserSignIn jsonBeanUserSignIn);
    }

    //取消订单
    public interface OnGetCancelOrderListener extends Listener {
        void onGetCancelOrder(JsonBeanCancelOrder jsonBeanCancelOrder);
    }

    //积分商城首页广告
    public interface OnGetLoadAdvertsListener extends Listener {
        void onGetLoadAdverts(JsonBeanLoadAdverts jsonBeanLoadAdverts);
    }

    // 加载积分商城排行榜
    public interface OnGetLoadCreditRankingListener extends Listener {
        void onGetLoadCreditRanking(JsonBeanCreditRanking jsonBeanCreditRanking);

    }

    //加载积分商城商品数据
    public interface OnGetIntergalShopCommodityListener extends Listener {
        void onGetIntergalShopCommodity(JsonBeanIntergalShopCommodityData jsonBeanIntergalShopCommodityData);
    }

    //加载单个积分商城产品信息
    public interface OnGetIntergalCommodityInfoListener extends Listener {
        void onGetIntergalCommodityInfo(JsonBeanIntergalCommodityInfo jsonBeanIntergalCommodityInfo);
    }

    //编辑店铺收藏状态
    public interface OnGetEditCollectListener extends Listener {
        void onGetEditCollect(JsonBeanEditCollect jsonBeanEditCollect);
    }

    //获取支付宝字窜
    public interface OnGetAliPayTextListener extends Listener {
        void onGetAliPayText(JsonBeanAliPayText jsonBeanAliPayText);
    }

    //加载区域信息
    public interface OnGetRegionalDataListener extends Listener {
        void onGetRegionalData(JsonBeanRegionalData jsonBeanRegionalData);
    }

    //加载店铺详情优惠券
    public interface OnGetStoreCouponsListener extends Listener {
        void onGetStoreCoupons(JsonBeanStoreCoupons jsonBeanStoreCoupons);
    }

    //领取优惠券
    public interface OnGetReceiveCouponsListener extends Listener {
        void onGetReceiveCoupons(JsonBeanReceiveCoupons jsonBeanReceiveCoupons);
    }

    //判断商户订单是否显示
    public interface OnGetShowListener extends Listener {
        void onGetShow(JsonBeanGetShow jsonBeanGetShow);
    }

    //用户提交评价
    public interface OnGetGoEvaluateListner extends Listener {
        void onGetGoEvaluate(JsonBeanGoEvaluate jsonBeanGoEvaluate);
    }

    //获取附近地址
    public interface OnGetNearbyAddressListener extends Listener {
        void onGetNearbyAddress(JsonBeanNearbyAddress jsonBeanNearbyAddress);
    }

    //上传本地图片
    public interface OnGetUploadImageListener extends Listener {
        void onGetUploadImage(JsonBeanUploadImage jsonBeanUploadImage);
    }

    //忘记密码
    public interface OnGetForgetPasswordListener extends Listener {
        void onGetForgetPassword(JsonBeanForgetPassword jsonBeanForgetPassword);

    }

    //忘记密码验证码
    public interface OnGetForgetPasswordCodeListener extends Listener {
        void onGetForGetPasswordCode(JsonBeanForgetPasswordCode jsonBeanForgetPasswordCode);
    }

    //普通用户是否有未读消息
    public interface OnGetConsumerUncheckMessageListener extends Listener {
        void onGetConsumerUncheckMessage(JsonBeanConsumerUncheckMessage jsonBeanConsumerUncheckMessage);
    }

    //商户用户是否有未读消息
    public interface OnGetMerchantUncheckMessageListener extends Listener {
        void onGetMerchantUncheckMessage(JsonBeanMerchantUncheckMessage jsonBeanMerchantUncheckMessage);
    }

    //申请退款
    public interface OnGetApplyRefundListener extends Listener {
        void onGetApplyRefund(JsonBeanApplyRefund jsonBeanApplyRefund);
    }

    //更多特价商品
    public interface OnGetDailySaleListener extends Listener {
        void onGetDailySale(JsonBeanDailySale jsonBeanDailySale);
    }

    //修改用户名
    public interface OnModifyUserNameListener extends Listener {
        void onModifyUserName(JsonBeanModifyUserName jsonBeanModifyUserName);
    }

    //修改用户头像
    public interface OnModifyUserPhotoListener extends Listener {
        void onModifyUserPhoto(JsonBeanModifyUserPhoto jsonBeanModifyUserPhoto);
    }

    //删除我的订单
    public interface OnDeleteMyOrderlistener extends Listener {
        void onDeleteMyOrder(JsonBeanDeleteMyOrder jsonBeanDeleteMyOrder);
    }

    //兑换积分商品
    public interface OnExchangeIntegralCommodityListener {
        void onExchangeIntegralCommodity(JsonBeanExchangeIntegralCommodity jsonBeanExchangeIntegralCommodity);
    }

    //获取要评论的商品数据
    public interface OnGetEvaluateProductListener extends Listener {
        void onGetEvaluateProduct(JsonBeanEvaluateProduct jsonBeanEvaluateProduct);
    }

    //获取商品详情更多评论
    public interface OnGetMoreCommentListener extends Listener {
        void onGetMoreComment(JsonBeanMoreComment jsonBeanMoreComment);
    }

    //个人中心我的评论
    public interface OnGetMyCommentListener extends Listener {
        void onGetMyComment(JsonBeanMyComment jsonBeanMyComment);
    }

    //发布评价
    public interface OnSubmitEvaluationListener extends Listener {
        void onSubmitEvaluation(JsonBeanSubmitEvaluation jsonBeanSubmitEvaluation);
    }

    //获取配送时间
    public interface OnGetDeliveryTimeListener extends Listener {
        void onGetDeliveryTime(JsonBeanDeliveryTime jsonBeanDeliveryTime);
    }
    //获取本店优惠券
    public interface OnGetCouponListener extends Listener {
        void onGetCoupon(SettlemCoupons settlemCoupons);
    }

    //商户订单按钮编辑接口
    public interface OnGetMerchantOrderEditListener extends Listener {
        void onGetMerchantOrderEdit(JsonBeanMerchantOrderEdit jsonBeanMerchantOrderEdit);
    }

    //退款原因列表
    public interface OnGetRefundReasonListener extends Listener {
        void onGetRefundReason(JsonBeanRefundReason jsonBeanRefundReason);
    }

    //获取便民服务类别列表
    public interface OnGetMyServiceCategoryListener extends Listener {
        void onGetMyServiceCategory(JsonBeanMyServiceCategory jsonBeanMyServiceCategory);
    }

    //二手品详情页获取联系人电话
    public interface OnGetSecondHandMobileListener extends Listener {
        void onGetSecondHandMobile(JsonBeanSecondHandMobile jsonBeanSecondHandMobile);
    }

    //便民服务详情页获取联系人电话
    public interface OnGetServiceMobileListener extends Listener {
        void onGetServiceMobile(JsonBeanServiceMobile jsonBeanServiceMobile);
    }

    //获取当前版本
    public interface OnGetCurrentVersionListener extends Listener {
        void onGetCurrentVersion(JsonBeanCurrentVersion jsonBeanCurrentVersion);
    }
}

