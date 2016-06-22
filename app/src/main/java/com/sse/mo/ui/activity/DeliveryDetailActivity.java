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
 * Created by Eric on 2016/5/24.
 */
public class DeliveryDetailActivity extends BaseActivity implements HasComponent<DeliveryComponent> {
    private static final String INSTANCE_STATE_PARAM_ORDER = "org.shiki.STATE_PARAM_ORDER_ID";
    private DeliveryComponent mDeliveryComponent;
    private OrderBean mOrder;

    public static Intent getCallingIntent(Context context, OrderBean order) {
        Intent callingIntent = new Intent(context, DeliveryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(LogisticApi.INTENT_EXTRA_PARAM_DELIVERY, order);
        callingIntent.putExtras(bundle);
        return callingIntent;
    }

    public static Intent getOutCallingIntent(OrderBean order) {
        Intent outcallingIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(LogisticApi.INTENT_EXTRA_PARAM_DELIVERY, order);
        outcallingIntent.putExtras(bundle);
        return outcallingIntent;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeInjector() {
        this.mDeliveryComponent = DaggerDeliveryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .deliveryModule(new DeliveryModule(this.mOrder))
                .build();
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

    @Override
    protected void initData() {

    }

    @Override
    public DeliveryComponent getComponent() {
        return mDeliveryComponent;
    }

    @Override
    public void onBackPressed() {
        setResult(LogisticApi.RESULT_OK, getOutCallingIntent(this.mOrder));
        finish();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delivery_sign, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delivery_sign:
                Intent intentToLaunch = SignActivity.getCallingIntent(this, mOrder);
                startActivityForResult(intentToLaunch, LogisticApi.INTENT_REQUEST_ORDER_SIGN_CODE);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
