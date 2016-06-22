package com.sse.mo.di.modules;

import android.content.Context;

import com.sse.mo.LogisticApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Maik on 2016/4/21.
 */
@Module
public class ApplicationModule {
    private final LogisticApplication application;

    public ApplicationModule(LogisticApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
}
