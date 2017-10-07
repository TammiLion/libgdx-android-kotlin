package com.tammidev.bodymon

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by troep on 9/24/17.
 */
@Module
class AppModule(val GTApplication: GTApplication) {
    @Provides
    @Singleton
    fun provideApp() = GTApplication
}