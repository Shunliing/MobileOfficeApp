package com.sse.mo.di.modules;

import android.app.Activity;

import com.sse.mo.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Maik on 2016/4/25.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return this.activity;
    }
}
