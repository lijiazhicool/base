package com.example.test;

import android.os.Bundle;

import com.example.test.base.BaseActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by LiJiaZhi on 16/8/3.
 *
 * 广告
 */

public class NativeAdActivity extends BaseActivity {

    //ad
    private NativeExpressAdView mAdView;
    //统计
    private final static String EVENT_AD_TYPE = "NativeAd_Clicked";
    private final static String EVENT_AD_NAME = "NativeAd";
    private final static String EVENT_AD_ID = "NativeAd_ID";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_native_ad;
    }

    @Override
    protected void initBundleExtra() {

    }

    @Override
    protected void findViewById() {
        mAdView = findView(R.id.adView);
    }

    @Override
    protected void initListeners() {
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                //统计
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, EVENT_AD_ID);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, EVENT_AD_NAME);
//                mFirebaseAnalytics.logEvent(EVENT_AD_TYPE, bundle);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAdView.loadAd(new AdRequest.Builder().build());
    }
}
