package com.sse.mo.di.components;

import android.content.Context;

import com.sse.mo.core.BaseActivity;
import com.sse.mo.di.modules.ApplicationModule;
import com.sse.mo.di.modules.LogisticApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Maik on 2016/4/21.
 */
@Singleton
@Component(modules = {ApplicationModule.class, LogisticApiModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
    Context getContext();
}
