package com.muran001.android_library_sample.lib.dagger;

import com.muran001.android_library_sample.Dagger2Activity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ArticleModule.class})
public interface ArticleComponent {

    void inject(Dagger2Activity activity);
}
