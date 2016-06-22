package com.sse.mo.model;

import com.sse.mo.bean.ProfileBean;
import com.sse.mo.bean.ResultBean;

import rx.Observable;

/**
 * Created by Maik on 2016/5/18.
 */
public interface IProfileModel {
    Observable<ResultBean<ProfileBean>> getProfileData(String expressId);
}
