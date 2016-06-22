package com.sse.mo.presenter.iview;

import com.sse.mo.bean.ProfileBean;
import com.sse.mo.core.mvp.MvpView;

/**
 * Created by Maik on 2016/5/18.
 */
public interface ProfileView extends MvpView {
    void renderProfileData(ProfileBean profile);
}
