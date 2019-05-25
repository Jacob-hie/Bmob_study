package com.hie2j.bmob_study;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"70ea92e6ce3d6ed62f59d84386cafde9");
    }
}
