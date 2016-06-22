package com.sse.mo.presenter.iview;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.core.mvp.MvpView;

import java.util.List;

/**
 * Created by Eric on 2016/5/9.
 */
public interface DeliveryListView extends MvpView {
    void renderDeliveryList(List<OrderBean> orderList);
    void viewDelivery(OrderBean order);
    void complaintHandingDelivery(OrderBean order);
    void setRefreshState(boolean isShow);
}
