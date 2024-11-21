package com.example.draggerdemo.dagger;

import com.example.draggerdemo.DaggerApplication;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpClientModule {
    @Singleton
    @Provides
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(DaggerApplication.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用 rxjava
                .addConverterFactory(GsonConverterFactory.create(new Gson()))// 使用 Gson
                .build();
    }
}
