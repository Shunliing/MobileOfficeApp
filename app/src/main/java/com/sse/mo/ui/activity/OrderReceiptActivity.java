package com.sse.mo.ui.activity;

import android.os.Bundle;

import com.sse.mo.R;
import com.sse.mo.core.BaseActivity;
import com.sse.mo.di.HasComponent;
import com.sse.mo.di.components.DaggerOrderReceiptComponent;
import com.sse.mo.di.components.OrderReceiptComponent;
import com.sse.mo.ui.fragment.OrderReceiptFragment;

/**
 * Created by Maik on 2016/5/16.
 */
public class OrderReceiptActivity extends BaseActivity implements HasComponent<OrderReceiptComponent> {
    private OrderReceiptComponent orderReceiptComponent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout;
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new OrderReceiptFragment());
        }
    }

    @Override
    protected void initData() {

    }

    private void initializeInjector() {
        this.orderReceiptComponent = DaggerOrderReceiptComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public OrderReceiptComponent getComponent() {
        return this.orderReceiptComponent;
    }
}
