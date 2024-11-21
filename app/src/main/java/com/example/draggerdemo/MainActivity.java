package com.example.draggerdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.draggerdemo.dagger.ApplicationModule;
import com.example.draggerdemo.dagger.ServiceModule;
import com.example.draggerdemo.data.AppVersionBean;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Inject
    DaggerApplication mDaggerApplication;
    @Inject
    ServiceManager mServiceManager;

    public Subscription mSubscription;
    private CompositeSubscription mCompositeSubscription;

    private Button mButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//                AppComponent appComponent = DaggerAppComponent
//                .builder()
//                .applicationModule(new ApplicationModule(this))
//                .serviceModule(new ServiceModule())
//                .build();
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(v -> {
            Subscription subscription = DaggerApplication
                    .getContext()
                    .getSystemRepository()
                    .getAppNewVersion()
                    .subscribe(new Subscriber<>() {

                        @Override
                        public void onCompleted() {
                            Log.d("MainActivity", "onCompleted: ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("MainActivity", "onError: "+e);

                        }

                        @Override
                        public void onNext(List<AppVersionBean> appVersionBeans) {
                            List<AppVersionBean> appVersionBean = appVersionBeans;
                            Log.d("MainActivity", "onNext: " + appVersionBean.toString());
                            for (AppVersionBean appVersionBean1 : appVersionBean) {
                                System.out.println("MainActivity VersionCode = " + appVersionBean1.getVersion_code());
                            }
                        }
                    });

            addSubscrebe(subscription);
//            new Handler(Looper.getMainLooper()).post(() -> {
//
//            });
        });
    }

    @Override
    public void onClick(View v) {
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        // 将所有 subscription 放入,集中处理
        mCompositeSubscription.add(subscription);
    }
}