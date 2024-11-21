package com.example.draggerdemo.dagger;

import com.example.draggerdemo.CommentClient;
import com.example.draggerdemo.DaggerApplication;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommentClient provideCommonService(Retrofit retrofit) {
//        builder.baseUrl(DaggerApplication.BASE_URL)// 域名
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用 rxjava
//                .addConverterFactory(GsonConverterFactory.create(new Gson()))// 使用 Gson
//                .build();
        return retrofit.create(CommentClient.class);
    }
}
