package com.example.draggerdemo;

import com.example.draggerdemo.data.AppVersionBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CommentClient {

    @GET("api/v2/plus-appversion")//https://merge.thinksns.com/api/v2/plus-appversion?version_code=262&type=android
    Observable<List<AppVersionBean>> getAppNewVersion(
            @Query("version_code") Integer version_code,
            @Query("type") String type
    );
}
