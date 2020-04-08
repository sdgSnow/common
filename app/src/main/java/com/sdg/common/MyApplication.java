package com.sdg.common;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.sdg.commonlibrary.provider.ContextProvider;

public class MyApplication extends Application {
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = ContextProvider.sContext;
        MultiDex.install(context);
    }
}
