package com.sse.mo.presenter;

import com.anupcowkur.reservoir.Reservoir;
import com.shiki.Xmpp.XmppUtils;
import com.shiki.utils.StringUtils;
import com.sse.mo.bean.UserBean;
import com.sse.mo.core.mvp.BasePresenter;
import com.sse.mo.model.impl.LoginModel;
import com.sse.mo.presenter.iview.LoginView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Maik on 2016/4/29.
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel loginModel;

    public LoginPresenter() {
        this.loginModel = LoginModel.getInstance();
    }

    public void showUserAndPasswd() {
        this.mCompositeSubscription.add(Observable.create(
                new Observable.OnSubscribe<UserBean>() {
                    @Override
                    public void call(Subscriber<? super UserBean> subscriber) {
                        try {
                            if (Reservoir.contains("userInfo")) {
                                UserBean userBean = Reservoir.get("userInfo", UserBean.class);
                                subscriber.onNext(userBean);
                            }
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        LoginPresenter.this.mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        LoginPresenter.this.getMvpView().setUserCode(userBean.getUsercode());
                        if (userBean.getIsRemember()) {
                            LoginPresenter.this.getMvpView().setPasswd(userBean.getUserPwd());
                            LoginPresenter.this.getMvpView().setRemeber(userBean.getIsRemember());
                        }
                    }
                }));
    }

    public void login() {
        final String usercode = this.getMvpView().getUserCode();
        final String passwd = this.getMvpView().getPasswd();
        if (StringUtils.isEmpty(usercode)||StringUtils.isEmpty(passwd)) {
            LoginPresenter.this.getMvpView().onFailure("账号或密码不能为空！");
            return;
        }
        final Boolean isRemember = this.getMvpView().getRemember();
        this.mCompositeSubscription.add(Observable.just(1)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return XmppUtils.getInstance().login(usercode,passwd);
                    }
                })
                /*.flatMap(new Func1<Boolean, Observable<String>>() {
                    @Override
                    public Observable<String> call(Boolean b) {
                        if(b){

                        }else{

                        }
                        if (resultBean.getStatusCode().equals(LogisticApi.FAILURE_DATA)) {
                            return Observable.just(resultBean.getStatusMessage());
                        } else {
                            return Observable.create(new Observable.OnSubscribe<String>() {
                                @Override
                                public void call(Subscriber<? super String> subscriber) {
                                    try {
                                        resultBean.getResultData().setIsRemember(isRemember);
                                        ReservoirUtils.getInstance().refresh("userInfo", resultBean.getResultData());
                                        subscriber.onNext(null);
                                        subscriber.onCompleted();
                                    } catch (Exception e) {
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        }
                    }
                })*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        LoginPresenter.this.mCompositeSubscription.remove(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginPresenter.this.getMvpView().hideLoginProgress();
                        LoginPresenter.this.getMvpView().onFailure(e.toString());
                    }

                    @Override
                    public void onNext(Boolean bool) {
                        if (bool) {
                            //XmppUtils.getInstance().getOfflineMsg();
                            XmppUtils.getInstance().sendP2PChat("admin","hello");
                            LoginPresenter.this.getMvpView().enterMain();
                        } else {
                            LoginPresenter.this.getMvpView().hideLoginProgress();
                            LoginPresenter.this.getMvpView().onFailure("用户名或密码错误");
                        }
                    }
                }));
    }
}
