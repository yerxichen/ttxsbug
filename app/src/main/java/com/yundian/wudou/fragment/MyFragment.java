package com.yundian.wudou.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yundian.wudou.R;
import com.yundian.wudou.activity.CouponsActivity;
import com.yundian.wudou.activity.HelpActivity;
import com.yundian.wudou.activity.LoginActivity;
import com.yundian.wudou.activity.MerchantOrderActivity;
import com.yundian.wudou.activity.MessageCenterActivity;
import com.yundian.wudou.activity.MyAllOrderActivity;
import com.yundian.wudou.activity.MyCollectActivity;
import com.yundian.wudou.activity.MyCommentActivity;
import com.yundian.wudou.activity.MySecondHandActivity;
import com.yundian.wudou.activity.MyServiceActivity;
import com.yundian.wudou.activity.MySettingActivity;
import com.yundian.wudou.activity.SelectAddressActivity;
import com.yundian.wudou.baseactivity.BaseActivity;
import com.yundian.wudou.customview.CircleImageView;
import com.yundian.wudou.data.FlagData;
import com.yundian.wudou.datawork.SharedpreferencesManager;
import com.yundian.wudou.network.JsonBeanConsumerUncheckMessage;
import com.yundian.wudou.network.JsonBeanGetShow;
import com.yundian.wudou.network.JsonBeanMerchantUncheckMessage;
import com.yundian.wudou.network.JsonBeanUserInformationData;
import com.yundian.wudou.network.JsonBeanUserSignIn;
import com.yundian.wudou.network.NetWorkOperate;
import com.yundian.wudou.publicinterface.NetWorkInterface;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by cookie on 2016/7/6.
 */
public class MyFragment extends Fragment implements NetWorkInterface.OnGetUserInformationListener,
        NetWorkInterface.OnGetShowListener, NetWorkInterface.OnGetConsumerUncheckMessageListener,
        NetWorkInterface.OnGetMerchantUncheckMessageListener, NetWorkInterface.OnGetUserSignInListener {

    private BaseActivity mBaseActivity;
    private SharedpreferencesManager manager;
    private NetWorkOperate netWorkOperate;

    /**
     * 商户,state = 0;
     * 普通用户,state = 1;
     * 配送员,state = 2;
     */
    private String token, state;

    @Bind(R.id.view_fragmentmy_lineone)
    View viewLineOne;
    @Bind(R.id.view_fragmentmy_linetwo)
    View viewLineTwo;
    @Bind(R.id.civ_fragmentmy_icon)
    CircleImageView circleImageView;
    @Bind(R.id.tv_fragmentmy_username)
    TextView tvUserName;
    @Bind(R.id.tv_fragmentmy_integral_content)
    TextView tvIntegral;
    @Bind(R.id.tv_fragmentmy_sign)
    TextView tvSignIn;
    @Bind(R.id.tv_fragmentmy_merchantorder)
    TextView tvMerchantOrder;
    @Bind(R.id.iv_fragmentmy_setting)
    ImageView ivSetting;
    @Bind(R.id.iv_fragmentmy_merchantmessage_icon)
    ImageView ivMerchantMsg;
    @Bind(R.id.iv_fragmentmy_message_icon)
    ImageView ivConsumerMsg;
    @Bind(R.id.tr_fragmentmy_allorder)
    TableRow mTableRowAllOrder;
    @Bind(R.id.tr_fragmentmy_merchantorder)
    TableRow mTableRowMerchantOrder;
    @Bind(R.id.tr_fragmentmy_merchantmessage)
    TableRow mTableRowMerchantMessage;
    @Bind(R.id.tr_fragmentmy_evaluate)
    TableRow mTableRowMyEvaluate;
    @Bind(R.id.tr_fragmentmy_helpcenter)
    TableRow mTableRowHelpCenter;
    @Bind(R.id.tr_fragmentmy_manageaddress)
    TableRow mTableRowAddress;
    @Bind(R.id.tr_fragmentmy_collection)
    TableRow mTableRowCollectShop;
    @Bind(R.id.tr_fragmentmy_secondhand)
    TableRow mTableRowSecondHand;
    @Bind(R.id.tr_fragmentmy_convenience_services)
    TableRow mTableRowConvenienceServices;
    @Bind(R.id.tr_fragmentmy_sharetofriends)
    TableRow mTableRowShare;
    @Bind(R.id.tr_fragmentmy_message)
    TableRow mTableRowMyMessage;
    @Bind(R.id.tr_fragmentmy_coupons)
    TableRow mTableRowCoupons;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_my, container, false);

        initialize(viewRoot);

        setEventListener();

        return viewRoot;
    }

    private void initialize(View view) {
        ButterKnife.bind(MyFragment.this, view);
        ShareSDK.initSDK(getContext(), "185c535764a03");
        manager = new SharedpreferencesManager(getContext());
        netWorkOperate = new NetWorkOperate(MyFragment.this);
    }

    @Override
    public void onResume() {
        super.onResume();
        token = manager.getToken();
        netWorkOperate.getUserInformation(token, "1");
        netWorkOperate.getShow(token);
        netWorkOperate.getConsumerUncheckMessage(token);
    }

    private void setEventListener() {
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MySettingActivity.class);
                startActivity(intent);
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netWorkOperate.getUserSignIn(token);
            }
        });

        mTableRowAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyAllOrderActivity.class);
                intent.putExtra(FlagData.FLAG_TITLE, "我的订单");
                startActivity(intent);
            }
        });

        mTableRowMerchantOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state.equals("0")) {
                    Intent intent = new Intent(getContext(), MerchantOrderActivity.class);
                    intent.putExtra(FlagData.FLAG_TITLE, "商户订单");
                    intent.putExtra(FlagData.FLAG_STATE, state);
                    startActivity(intent);
                } else if (state.equals("2")) {
                    Intent intent = new Intent(getContext(), MerchantOrderActivity.class);
                    intent.putExtra(FlagData.FLAG_TITLE, "配送员订单");
                    intent.putExtra(FlagData.FLAG_STATE, state);
                    startActivity(intent);
                }
            }
        });

        mTableRowMerchantMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageCenterActivity.class);
                intent.putExtra(FlagData.FLAG_TYPE, "4");
                intent.putExtra(FlagData.FLAG_MESSAGE_SID, "0");
                intent.putExtra(FlagData.FLAG_MESSAGE_PID, "0");
                startActivity(intent);
            }
        });

        mTableRowCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CouponsActivity.class);
                getContext().startActivity(intent);
            }
        });

        mTableRowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectAddressActivity.class);
                getContext().startActivity(intent);
            }
        });

        mTableRowMyEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyCommentActivity.class);
                getContext().startActivity(intent);
            }
        });

        mTableRowCollectShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyCollectActivity.class);
                getContext().startActivity(intent);
            }
        });

        mTableRowSecondHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySecondHandActivity.class);
                startActivity(intent);
            }
        });

        mTableRowConvenienceServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyServiceActivity.class);
                startActivity(intent);
            }
        });

        mTableRowMyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageCenterActivity.class);
                intent.putExtra(FlagData.FLAG_TYPE, "1");
                intent.putExtra(FlagData.FLAG_MESSAGE_SID, "0");
                intent.putExtra(FlagData.FLAG_MESSAGE_PID, "0");
                getContext().startActivity(intent);
            }
        });

        mTableRowShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        mTableRowHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HelpActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onGetUserInformationData(JsonBeanUserInformationData jsonBeanUserInformationData) {
        Glide.with(getContext()).load(FlagData.FLAG_IMGADDRESS + jsonBeanUserInformationData.getAvatar()).into(circleImageView);
        tvUserName.setText(jsonBeanUserInformationData.getUsername());
        tvIntegral.setText(jsonBeanUserInformationData.getCredits());
    }

    @Override
    public void onGetShow(JsonBeanGetShow jsonBeanGetShow) {
        state = jsonBeanGetShow.getShow();
        if (state.equals("0")) {
            viewLineOne.setVisibility(View.VISIBLE);
            viewLineTwo.setVisibility(View.VISIBLE);
            mTableRowMerchantOrder.setVisibility(View.VISIBLE);
            mTableRowMerchantMessage.setVisibility(View.VISIBLE);
            netWorkOperate.getMerchantUncheckMessage(token);
        } else if (state.equals("1")) {
            viewLineOne.setVisibility(View.GONE);
            viewLineTwo.setVisibility(View.GONE);
            mTableRowMerchantOrder.setVisibility(View.GONE);
            mTableRowMerchantMessage.setVisibility(View.GONE);
        } else if (state.equals("2")) {
            viewLineOne.setVisibility(View.VISIBLE);
            mTableRowMerchantOrder.setVisibility(View.VISIBLE);
            viewLineTwo.setVisibility(View.GONE);
            mTableRowMerchantMessage.setVisibility(View.GONE);
            tvMerchantOrder.setText(getResources().getString(R.string.distribution_order));
        }
    }

    @Override
    public void onGetMerchantUncheckMessage(JsonBeanMerchantUncheckMessage jsonBeanConsumerUncheckMessage) {
        if (jsonBeanConsumerUncheckMessage.getIsreadcount().equals("0")) {
            ivMerchantMsg.setImageResource(R.drawable.message_checked);
        } else {
            ivMerchantMsg.setImageResource(R.drawable.message_unchecked);
        }
    }

    @Override
    public void onGetConsumerUncheckMessage(JsonBeanConsumerUncheckMessage jsonBeanConsumerUncheckMessage) {
        if (jsonBeanConsumerUncheckMessage.getIsreadcount().equals("0")) {
            ivConsumerMsg.setImageResource(R.drawable.message_checked);
        } else {
            ivConsumerMsg.setImageResource(R.drawable.message_unchecked);
        }
    }

    @Override
    public void onGetUserSignIn(JsonBeanUserSignIn jsonBeanUserSignIn) {
        Toast.makeText(getContext(), jsonBeanUserSignIn.getMsg(), Toast.LENGTH_SHORT).show();
        netWorkOperate.getUserInformation(token, "1");
    }

    @Override
    public void onFailure(String msg) {
        if (msg.equals("用户未登录")) {
            showMessageDialog(msg);
        } else {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void showMessageDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showShare() {
        ShareSDK.initSDK(getActivity(), "21f98d1932628");
        // ShareSDK.initSDK();
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        oks.setTitle("天天蔬心");
        oks.setText("创建美好健康的新生活，从天天蔬心开始");
        oks.setImageUrl("http://admin.ttsxin.com/fenxiang/fx.png");
        oks.setUrl("http://download.ttsxin.com/");
        oks.setSite("天天蔬心");
        oks.setSiteUrl("http://admin.ttsxin.com/fenxiang/fx.png");
        // 启动分享GUI
        oks.show(getContext());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) context;
        mBaseActivity.setTitleBarVisibility(true);
        mBaseActivity.setTitle(R.string.my_account);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mBaseActivity.setTitleBarVisibility(true);
            mBaseActivity.setTitle(R.string.my_account);
        }
    }
}
