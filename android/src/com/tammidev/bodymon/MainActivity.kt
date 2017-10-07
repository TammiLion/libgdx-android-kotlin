package com.tammidev.bodymon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var configHelper: RemoteConfigHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup();
    }

    private fun setup() {
        button.setOnClickListener({ startActivity(Intent(this, AndroidLauncher::class.java)) })
    }
}
