package com.example.draggerdemo;

import android.app.Application;

import com.example.draggerdemo.dagger.ApplicationModule;
import com.example.draggerdemo.dagger.HttpClientModule;
import com.example.draggerdemo.dagger.ServiceModule;
import com.example.draggerdemo.data.SystemRepository;

import javax.inject.Inject;

public class DaggerApplication extends Application {

    private ApplicationModule mAppModule;
    private ServiceManager mServiceManager;
    private HttpClientModule mHttpClientModule;
    protected static DaggerApplication mApplication;

    @Inject
    protected SystemRepository mSystemRepository;

    public static final String TAG = "DaggerApplication";
//    public static final String BASE_URL2 = "http://api.k780.com/";
    public static final String BASE_URL = "https://merge.thinksns.com/";
    @Override
    public void onCreate() {
        super.onCreate();
        this.mAppModule = new ApplicationModule(this);
        this.mHttpClientModule = new HttpClientModule();
        mApplication = this;
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .applicationModule(getmAppModule())
                .httpClientModule(getmHttpClientModule())
                .serviceModule(getmServiceModule())
                .build();
        AppComponentHolder.setAppComponent(appComponent);
        appComponent.inject(this);
    }

    public static class AppComponentHolder {
        private static AppComponent sAppComponent;
        public static void setAppComponent(AppComponent appComponent) {
            sAppComponent = appComponent;
        }
        public static AppComponent getAppComponent() {
            return sAppComponent;
        }
    }

    public ApplicationModule getmAppModule() {
        return mAppModule;
    }

    public ServiceModule getmServiceModule() {
        return new ServiceModule();
    }

    public HttpClientModule getmHttpClientModule() {
        return mHttpClientModule;
    }

    public SystemRepository getSystemRepository() {
        return mSystemRepository;
    }

    public static DaggerApplication getContext() {
        return mApplication;
    }
}
