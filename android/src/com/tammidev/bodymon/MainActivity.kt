package com.tammidev.bodymon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var configHelper: RemoteConfigHelper
    @Inject lateinit var api: Api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as GTApplication).getAppComponent().inject(this);
        getRandomNumber();
        setup();
    }

    private fun getRandomNumber() {
        val disposable: Disposable = api.helloWorld()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { Log.d("Debug", "doOnNext") }
                .subscribe({ summonResponse: SummonResponse? ->
                    Log.d("Debug", "test" + summonResponse?.message + " - " + summonResponse?.result)
                    Toast.makeText(this, summonResponse?.message + " - " + summonResponse?.result, Toast.LENGTH_LONG).show()
                }, { error -> Log.d("Debug", error.message) })
    }

    private fun setup() {
        button.setOnClickListener({ startActivity(Intent(this, AndroidLauncher::class.java)) })
    }
}
