package com.tammidev.bodymon

import dagger.Component
import javax.inject.Singleton

/**
 * Created by troep on 9/24/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(GTApplication: GTApplication)
    fun inject(activity: AndroidLauncher)
}