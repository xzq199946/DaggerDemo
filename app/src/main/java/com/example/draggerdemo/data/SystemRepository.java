package com.example.draggerdemo.data;

import android.content.Context;
import android.content.pm.PackageManager;

import com.example.draggerdemo.CommentClient;
import com.example.draggerdemo.DaggerApplication;
import com.example.draggerdemo.ServiceManager;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SystemRepository {

    private CommentClient mCommonClient;
    private DaggerApplication mApplication;

    @Inject
    public SystemRepository(ServiceManager serviceManager, DaggerApplication application) {
        mCommonClient = serviceManager.getCommentClient();
        mApplication = application;
    }


    /**
     * 获取新版本
     *
     * @return
     */
    public Observable<List<AppVersionBean>> getAppNewVersion() {

        return mCommonClient.getAppNewVersion(262, "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static int getVersionCode(Context context) {
        int versionCode;
        try {
            versionCode = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(),
                            0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }
}
