package com.sse.mo.model;

import com.sse.mo.bean.MessageBean;
import com.sse.mo.bean.ResultBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Maik on 2016/5/10.
 */
public interface IMessageModel {
    Observable<ResultBean<List<MessageBean>>> getMessageList(String expressKey);
    Observable<ResultBean<MessageBean>> getMessageDetail(String expressId, String messageId);
}
