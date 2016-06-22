package com.sse.mo.presenter.iview;

import com.sse.mo.bean.MessageBean;
import com.sse.mo.core.mvp.MvpView;

import java.util.List;

/**
 * Created by Maik on 2016/5/9.
 */
public interface MessageListView extends MvpView {
    void renderMessageList(List<MessageBean> messageData);
    void viewMessage(MessageBean message);
    void setRefreshState(boolean isShow);
}
