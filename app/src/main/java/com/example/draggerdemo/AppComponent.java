package com.example.draggerdemo;

import android.app.Application;

import com.example.draggerdemo.dagger.ApplicationModule;
import com.example.draggerdemo.dagger.HttpClientModule;
import com.example.draggerdemo.dagger.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ServiceModule.class, HttpClientModule.class})
public interface AppComponent extends InjectComponent<DaggerApplication>{

    @Override
    void inject(DaggerApplication injectData);

    DaggerApplication Application();

    ServiceManager serviceManager();
}
