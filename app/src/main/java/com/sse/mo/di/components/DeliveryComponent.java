package com.sse.mo.di.components;

import com.sse.mo.di.PerActivity;
import com.sse.mo.di.modules.ActivityModule;
import com.sse.mo.di.modules.DeliveryModule;
import com.sse.mo.ui.fragment.DeliveryAppointmentFragment;
import com.sse.mo.ui.fragment.DeliveryComplaintFragment;
import com.sse.mo.ui.fragment.DeliveryComplaintListFragment;
import com.sse.mo.ui.fragment.DeliveryDetailFragment;
import com.sse.mo.ui.fragment.DeliverySignFragment;

import dagger.Component;

/**
 * Created by Maik on 2016/5/9.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, DeliveryModule.class})
public interface DeliveryComponent extends ActivityComponent {
    void inject(DeliveryDetailFragment deliveryDetailFragment);
    void inject(DeliveryAppointmentFragment deliveryAppointmentFragment);
    void inject(DeliveryComplaintFragment deliveryComplaintFragment);
    void inject(DeliverySignFragment deliverySignFragment);
    void inject(DeliveryComplaintListFragment deliveryComplaintListFragment);
}
