package com.sse.mo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.sse.mo.R;
import com.sse.mo.bean.MessageBean;
import com.sse.mo.core.BaseActivity;
import com.sse.mo.di.HasComponent;
import com.sse.mo.di.components.DaggerMessageComponent;
import com.sse.mo.di.components.MessageComponent;
import com.sse.mo.di.modules.MessageModule;
import com.sse.mo.logistic.LogisticApi;
import com.sse.mo.ui.fragment.MessageDetailFragment;

/**
 * Created by Maik on 2016/5/12.
 */
public class MessageDetailActivity extends BaseActivity implements HasComponent<MessageComponent> {
    private static final String INSTANCE_STATE_PARAM_MESSAGE = "org.shiki.STATE_PARAM_MESSAGE_ID";
    private MessageComponent messageComponent;
    private MessageBean message;

    public static Intent getCallingIntent(Context context, MessageBean message) {
        Intent callingIntent = new Intent(context, MessageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(LogisticApi.INTENT_EXTRA_PARAM_MESSAGE, message);
        callingIntent.putExtras(bundle);
        return callingIntent;
    }

    public static Intent getOutCallingIntent(MessageBean message) {
        Intent outcallingIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(LogisticApi.INTENT_EXTRA_PARAM_MESSAGE, message);
        outcallingIntent.putExtras(bundle);
        return outcallingIntent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelable(INSTANCE_STATE_PARAM_MESSAGE, this.message);
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

    @Override
    protected void initViews(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.message = getIntent().getParcelableExtra(LogisticApi.INTENT_EXTRA_PARAM_MESSAGE);
            addFragment(R.id.fragmentContainer, new MessageDetailFragment());
        } else {
            this.message = savedInstanceState.getParcelable(INSTANCE_STATE_PARAM_MESSAGE);
        }
        initializeInjector();
    }

    @Override
    protected void initData() {

    }

    private void initializeInjector() {
        this.messageComponent = DaggerMessageComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .messageModule(new MessageModule(this.message))
                .build();
    }

    @Override
    public MessageComponent getComponent() {
        return messageComponent;
    }

    @Override
    public void onBackPressed() {
        setResult(LogisticApi.INTENT_REQUEST_MESSAGE_CODE, getOutCallingIntent(this.message));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }
}
