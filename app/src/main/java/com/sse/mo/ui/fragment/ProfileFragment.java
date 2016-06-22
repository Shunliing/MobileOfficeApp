package com.sse.mo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sse.mo.R;
import com.sse.mo.bean.ProfileBean;
import com.sse.mo.core.BaseFragment;
import com.sse.mo.di.components.MainComponent;
import com.sse.mo.presenter.ProfilePresenter;
import com.sse.mo.presenter.iview.ProfileView;
import com.sse.mo.ui.activity.DeliveryComplaintListActivity;
import com.sse.mo.ui.activity.MainActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Eric on 2016/5/12.
 */
public class ProfileFragment extends BaseFragment implements ProfileView {
    @Bind(R.id.tv_username)
    TextView tvUserName;
    @Bind(R.id.tv_usercode)
    TextView tvUserCode;
    @Bind(R.id.tv_userorga)
    TextView tvUserOrga;
    @Bind(R.id.tv_usertel)
    TextView tvUserTel;
    @Bind(R.id.tv_dispatch)
    TextView tvDispatch;
    @Bind(R.id.tv_finish)
    TextView tvFinish;
    @Bind(R.id.tv_remind)
    TextView tvRemind;
    @Inject
    ProfilePresenter profilePresenter;
    @Bind(R.id.tv_p_complaint)
    TextView mTvPComplaint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MainComponent.class).inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.profilePresenter.attachView(this);
        if (savedInstanceState == null)
            this.profilePresenter.loadProfileData();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initViews(View self, Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.profilePresenter.detachView();
    }

    @Override
    public void renderProfileData(ProfileBean profile) {
        tvUserName.setText(profile.getUserBean().getUserName());
        tvUserCode.setText(profile.getUserBean().getUsercode());
        tvUserOrga.setText(profile.getUserBean().getUserOrga());
        tvUserTel.setText(profile.getUserBean().getUserTel());
        tvDispatch.setText(profile.getDispatchNum() + "\n待配送");
        tvFinish.setText(profile.getFinishNum() + "\n已完成");
        tvRemind.setText(profile.getRemindNum() + "\n催单量");
    }

    @OnClick(R.id.tv_p_complaint)
    public void onComplaintListClick() {
        Intent intent = DeliveryComplaintListActivity.getCallingIntent(getActivity());
        startActivity(intent);
    }

    @OnClick(R.id.tv_order_all)
    public void showAllOrder() {
        ((MainActivity)getActivity()).switchDelivery();
    }

    @OnClick(R.id.tv_remind_all)
    public void showAllRemind() {
        ((MainActivity)getActivity()).switchDelivery(1);
    }
}
