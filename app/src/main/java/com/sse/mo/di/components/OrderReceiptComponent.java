package com.sse.mo.di.components;

import com.sse.mo.di.PerActivity;
import com.sse.mo.di.modules.ActivityModule;
import com.sse.mo.ui.fragment.OrderReceiptFragment;

import dagger.Component;

/**
 * Created by Maik on 2016/5/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface OrderReceiptComponent extends ActivityComponent {
    void inject(OrderReceiptFragment orderReceiptFragment);
}
