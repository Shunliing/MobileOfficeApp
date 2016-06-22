package com.sse.mo.presenter.iview;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.core.mvp.MvpView;

import java.util.List;

/**
 * Created by Maik on 2016/5/16.
 */
public interface OrderReceiptView extends MvpView {
    void renderOrderList(List<OrderBean> orderData);
    void setRefreshState(boolean isShow);
    void refreshOrderList();
}
