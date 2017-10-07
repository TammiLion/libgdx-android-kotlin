package com.tammidev.bodymon

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigHelper @Inject
constructor() {

    companion object {
        private const val TEST_BOOLEAN = "holiday_promo_enabled"
        const val CACHE_EXPERATION_ONE_HOUR: Long = 3600
        const val CACHE_EXPERATION_DEVELOPER_MODE: Long = 0

    }

    val remoteConfig: FirebaseRemoteConfig

    init {
        remoteConfig = FirebaseRemoteConfig.getInstance()
        setup()
    }

    private fun setup() {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()
        remoteConfig.setConfigSettings(configSettings)
        remoteConfig.setDefaults(R.xml.remote_config_defaults)
        test()
        fetchValues()
    }

    private fun fetchValues() {
        var cacheExpiration: Long = CACHE_EXPERATION_ONE_HOUR
        if (remoteConfig.info.configSettings.isDeveloperModeEnabled) {
            cacheExpiration = CACHE_EXPERATION_DEVELOPER_MODE
        }

        remoteConfig.fetch(cacheExpiration).addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                remoteConfig.activateFetched()
                Log.d("Debug", "successfull")
            } else {
                Log.d("Debug", task.exception.toString())
            }
            Log.d("Debug", "blah")
            test()
        }).addOnFailureListener({ exception -> Log.d("Debug", exception.localizedMessage) })
    }

    fun test() {
        Log.d("Debug", "Is holiday promo enabled: " + remoteConfig.getBoolean(TEST_BOOLEAN))
    }
}
