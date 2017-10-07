package com.tammidev.bodymon

import android.app.Application

class GTApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        //MobileAds.initialize(this, "ca-app-pub-8794565842069067~2344003370");
    }
}
