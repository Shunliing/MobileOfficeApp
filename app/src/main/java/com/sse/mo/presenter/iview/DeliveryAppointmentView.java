package com.sse.mo.presenter.iview;

import com.sse.mo.bean.OrderBean;
import com.sse.mo.core.mvp.MvpView;

/**
 * Created by Eric on 2016/5/12.
 */
public interface DeliveryAppointmentView extends MvpView {
    void loadAppointment(OrderBean order);
    String getAppointmentReason();
    String getAppointmentTime();
    void submitDone();
    void showSubmitProgress();
    void closeSubmitProgress();
}
