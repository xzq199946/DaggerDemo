package com.example.draggerdemo.dagger;

import com.example.draggerdemo.DaggerApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private DaggerApplication mApplication;
    public ApplicationModule(DaggerApplication application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public DaggerApplication provideApplication() {
        return mApplication;
    }
}
