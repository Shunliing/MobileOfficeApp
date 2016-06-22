package com.sse.mo.model;

import com.sse.mo.bean.ResultBean;
import com.sse.mo.bean.VersionBean;

import rx.Observable;

/**
 * Created by Maik on 2016/5/3.
 */
public interface IAppModel {

    Observable<ResultBean<VersionBean>> getVersion();
    //Observable<ResponseBody> downloadAPK();
}
