package com.tammidev.bodymon

import android.app.Application

class GTApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .apiModule(ApiModule(Constants.BASE_URL))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        //MobileAds.initialize(this, "ca-app-pub-8794565842069067~2344003370");
    }

    fun getAppComponent(): AppComponent {
        return component;
    }
}
