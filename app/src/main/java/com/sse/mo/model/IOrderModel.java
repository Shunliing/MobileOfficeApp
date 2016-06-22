package com.sse.mo.model;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.bean.ResultBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Maik on 2016/5/16.
 */
public interface IOrderModel {
    Observable<ResultBean<List<OrderBean>>> receiptOrderList(String expressId, String searchKey);
    Observable<ResultBean> receiptOrders(String deliveryCodes);
}
