package com.sse.mo.model.impl;

import com.sse.mo.bean.ResultBean;
import com.sse.mo.bean.UserBean;
import com.sse.mo.logistic.LogisticMain;
import com.sse.mo.model.ILoginModel;

import rx.Observable;

/**
 * Created by Maik on 2016/4/28.
 */
public class LoginModel implements ILoginModel {
    private static final LoginModel instance = new LoginModel();

    public static LoginModel getInstance() {
        return instance;
    }

    private LoginModel() {
    }

    @Override
    public Observable<ResultBean<UserBean>> login(String usercode, String password) {
        return LogisticMain.getInstance().getLogisticService().login(usercode, password);
    }
}
