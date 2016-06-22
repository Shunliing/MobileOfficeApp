package com.sse.mo.di.components;

import com.sse.mo.di.PerActivity;
import com.sse.mo.di.modules.ActivityModule;
import com.sse.mo.ui.fragment.DeliveryListFragment;
import com.sse.mo.ui.fragment.HomeFragment;
import com.sse.mo.ui.fragment.ProfileFragment;

import dagger.Component;

/**
 * Created by Maik on 2016/5/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface MainComponent extends ActivityComponent {
    void inject(ProfileFragment profileFragment);
    void inject(DeliveryListFragment deliveryListFragment);
    void inject(HomeFragment homeFragment);
}
