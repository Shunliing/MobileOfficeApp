package com.sse.mo.di.modules;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 2016/5/12.
 */
@Module
public class DeliveryModule {
    private OrderBean mOrderBean;

    public DeliveryModule() {}

    public DeliveryModule(OrderBean order) {
        this.mOrderBean = order;
    }

    @Provides
    @PerActivity
    OrderBean provideDelivery() {
        return this.mOrderBean;
    }
}
