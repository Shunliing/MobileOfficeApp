package com.sse.mo.di.modules;

import android.content.Context;

import com.sse.mo.MoApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Maik on 2016/4/21.
 */
@Module
public class ApplicationModule {
    private final MoApplication application;

    public ApplicationModule(MoApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
}
