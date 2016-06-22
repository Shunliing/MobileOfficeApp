package com.sse.mo.model.impl;

import com.sse.mo.bean.ProfileBean;
import com.sse.mo.bean.ResultBean;
import com.sse.mo.logistic.LogisticMain;
import com.sse.mo.model.IProfileModel;

import rx.Observable;

/**
 * Created by Maik on 2016/5/18.
 */
public class ProfileModel implements IProfileModel {
    private static final ProfileModel instance = new ProfileModel();

    public static ProfileModel getInstance() {
        return instance;
    }

    private ProfileModel() {
    }

    @Override
    public Observable<ResultBean<ProfileBean>> getProfileData(String expressId) {
        return LogisticMain.getInstance().getLogisticService().getProfileData(expressId);
    }
}
