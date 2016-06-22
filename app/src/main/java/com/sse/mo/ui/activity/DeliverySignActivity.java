package com.sse.mo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sse.mo.R;
import com.sse.mo.bean.OrderBean;
import com.sse.mo.core.BaseActivity;
import com.sse.mo.di.HasComponent;
import com.sse.mo.di.components.DaggerDeliveryComponent;
import com.sse.mo.di.components.DeliveryComponent;
import com.sse.mo.di.modules.DeliveryModule;
import com.sse.mo.logistic.LogisticApi;
import com.sse.mo.ui.fragment.DeliveryDetailFragment;

/**
 * Created by Eric on 2016/5/25.
 */
public class DeliverySignActivity extends BaseActivity implements HasComponent<DeliveryComponent> {
    private static final String INSTANCE_STATE_PARAM_ORDER = "org.shiki.STATE_PARAM_ORDER_ID";
    private DeliveryComponent mDeliveryComponent;
    private OrderBean mOrder;

    public static Intent getCallingIntent(Context context, OrderBean order) {
        Intent callingIntent = new Intent(context, DeliverySignActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(LogisticApi.INTENT_EXTRA_PARAM_DELIVERY, order);
        callingIntent.putExtras(bundle);
        return callingIntent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelable(INSTANCE_STATE_PARAM_ORDER, this.mOrder);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.mOrder = getIntent().getParcelableExtra(LogisticApi.INTENT_EXTRA_PARAM_DELIVERY);
            addFragment(R.id.fragmentContainer, new DeliveryDetailFragment());
        } else {
            this.mOrder = savedInstanceState.getParcelable(INSTANCE_STATE_PARAM_ORDER);
        }
        initializeInjector();
    }

    private void initializeInjector() {
        this.mDeliveryComponent = DaggerDeliveryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .deliveryModule(new DeliveryModule(this.mOrder))
                .build();
    }

    @Override
    protected void initData() {

    }

    @Override
    public DeliveryComponent getComponent() {
        return mDeliveryComponent;
    }
}
