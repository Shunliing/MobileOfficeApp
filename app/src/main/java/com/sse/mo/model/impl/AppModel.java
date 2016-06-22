package com.sse.mo.model.impl;

import com.sse.mo.bean.ResultBean;
import com.sse.mo.bean.VersionBean;
import com.sse.mo.logistic.LogisticMain;
import com.sse.mo.model.IAppModel;

import rx.Observable;

/**
 * Created by Maik on 2016/5/3.
 */
public class AppModel implements IAppModel {
    private static final AppModel instance = new AppModel();

    public static AppModel getInstance() {
        return instance;
    }

    private AppModel() {
        //MoApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<ResultBean<VersionBean>> getVersion() {
        return LogisticMain.getInstance().getLogisticService().getVersion();
    }

//    @Override
//    public Observable<ResponseBody> downloadAPK() {
//        return LogisticMain.getInstance().getLogisticService().receiptOrders("");
//    }
}
