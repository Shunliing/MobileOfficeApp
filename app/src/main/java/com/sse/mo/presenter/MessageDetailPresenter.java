package com.sse.mo.presenter;

import com.anupcowkur.reservoir.Reservoir;
import com.sse.mo.bean.MessageBean;
import com.sse.mo.bean.ResultBean;
import com.sse.mo.bean.UserBean;
import com.sse.mo.core.mvp.BasePresenter;
import com.sse.mo.model.impl.MessageModel;
import com.sse.mo.presenter.iview.MessageDetailView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Maik on 2016/5/12.
 */
public class MessageDetailPresenter extends BasePresenter<MessageDetailView> {
    private MessageModel messageModel;
    private MessageBean message;

    @Inject
    public MessageDetailPresenter(MessageBean message) {
        this.messageModel = MessageModel.getInstance();
        this.message = message;
    }

    public void loadDetailData() {
        this.getMessageDetail();
    }

    private void getMessageDetail() {
        this.mCompositeSubscription.add(Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            if (Reservoir.contains("userInfo")) {
                                UserBean userBean = Reservoir.get("userInfo", UserBean.class);
                                subscriber.onNext(userBean.getUserId());
                            }
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .flatMap(new Func1<String, Observable<ResultBean<MessageBean>>>() {
                    @Override
                    public Observable<ResultBean<MessageBean>> call(String expressId) {
                        return messageModel.getMessageDetail(expressId, message.getMessageId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultBean<MessageBean>>() {
                    @Override
                    public void onCompleted() {
                        MessageDetailPresenter.this.mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.toString());
                    }

                    @Override
                    public void onNext(ResultBean<MessageBean> resultBean) {
                        message.setMessageFlag("2");
                        MessageDetailPresenter.this.getMvpView().renderMessageDetail(resultBean.getResultData());
                    }
                }));
    }
}
