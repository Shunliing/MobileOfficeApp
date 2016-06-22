package com.sse.mo.model;

import com.sse.mo.bean.ResultBean;
import com.sse.mo.bean.UserBean;

import rx.Observable;

/**
 * Created by Maik on 2016/4/25.
 */
public interface ILoginModel {
    Observable<ResultBean<UserBean>> login(String usercode, String password);
}
