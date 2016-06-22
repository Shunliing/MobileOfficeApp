package com.sse.mo.di.components;

import com.sse.mo.di.PerActivity;
import com.sse.mo.di.modules.ActivityModule;
import com.sse.mo.di.modules.MessageModule;
import com.sse.mo.ui.fragment.MessageDetailFragment;
import com.sse.mo.ui.fragment.MessageListFragment;

import dagger.Component;

/**
 * Created by Maik on 2016/5/9.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MessageModule.class})
public interface MessageComponent extends ActivityComponent {
    void inject(MessageListFragment messageListFragment);
    void inject(MessageDetailFragment messageDetailFragment);
}
