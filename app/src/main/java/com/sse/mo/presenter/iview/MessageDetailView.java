package com.sse.mo.presenter.iview;

import com.sse.mo.bean.MessageBean;
import com.sse.mo.core.mvp.MvpView;

/**
 * Created by Maik on 2016/5/12.
 */
public interface MessageDetailView extends MvpView {
    void renderMessageDetail(MessageBean message);
    void setTitleWithFormat(String title);
    void setSubTitleWithFormat(String subtitle);
    void setContentWithFormat(String content);
}
