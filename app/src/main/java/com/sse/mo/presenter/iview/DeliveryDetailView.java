package com.sse.mo.presenter.iview;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.core.mvp.MvpView;

/**
 * Created by Eric on 2016/5/12.
 */
public interface DeliveryDetailView extends MvpView {
    void renderOrderDetail(OrderBean order);
    void setOrdCodeWithFormat(String ordCode);
    void setDeliveryCodeWithFormat(String deliveryCode);
    void setLogisticCodeWithFormat(String logisticCode);
    void setDeliveryAddrWithFormat(String deliveryAddr);
    void setRecipientWithFormat(String recipient);
    void setOrdTelWithFormat(String ordTel);
    void setExprItemWithFormat(String exprItem);
    void setAppointmentWithFormat(String appointment);
    void setOrdRemarkWithFormat(String ordRemark);
    void setCallVisibility(String ordTel);
    void makeCall(String ordTel);
    void changeAppointment(OrderBean order);
    void sign(OrderBean order);
    String getBackReason();
    void showSubmitProgress();
    void closeSubmitProgress();
    void submitDone(OrderBean order);
}
