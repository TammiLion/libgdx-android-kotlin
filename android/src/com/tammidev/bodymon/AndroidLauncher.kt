package com.tammidev.bodymon

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import javax.inject.Inject

/**
 * Created by troep on 9/30/17.
 */

class AndroidLauncher : AndroidApplication() {

    @Inject
    lateinit var remoteConfigHelper: RemoteConfigHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleDeeplinking()
        initializeGame();
    }

    private fun initializeGame() {
        val config = AndroidApplicationConfiguration()
        initialize(BodymonGame(), config)
    }

    private fun handleDeeplinking() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(intent)
                .addOnSuccessListener(this, { pendingDynamicLinkData ->
                    // Get deep link from result (may be null if no link is found)
                    var deepLink: Uri? = null
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink()
                    }

                    Log.d("Debug", "deeplinktest deeplink: " + deepLink)


                    // Handle the deep link. For example, open the linked
                    // content, or apply promotional credit to the user's
                    // account.
                    // ...

                    // ...
                })
                .addOnFailureListener(this, { e ->
                    Log.w("Debug", "deeplinktest getDynamicLink:onFailure", e)
                })
    }
}