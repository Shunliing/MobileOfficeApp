package com.sse.mo.model.impl;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.bean.ResultBean;
import com.sse.mo.logistic.LogisticMain;
import com.sse.mo.model.IOrderModel;

import java.util.List;

import rx.Observable;

/**
 * Created by Maik on 2016/5/16.
 */
public class OrderModel implements IOrderModel {
    private static final OrderModel instance = new OrderModel();

    public static OrderModel getInstance() {
        return instance;
    }

    private OrderModel() {
    }

    @Override
    public Observable<ResultBean<List<OrderBean>>> receiptOrderList(String expressId, String searchKey) {
        return LogisticMain.getInstance().getLogisticService().receiptOrderList(expressId, searchKey);
    }

    @Override
    public Observable<ResultBean> receiptOrders(String deliveryCodes) {
        return LogisticMain.getInstance().getLogisticService().receiptOrders(deliveryCodes);
    }
}
