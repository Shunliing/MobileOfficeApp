package com.sse.mo.di.components;

import android.app.Activity;

import com.sse.mo.di.PerActivity;
import com.sse.mo.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Maik on 2016/4/25.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
