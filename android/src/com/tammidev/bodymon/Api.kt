package com.tammidev.bodymon

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by troep on 11/14/17.
 */

interface Api {

    @GET("helloWorld")
    fun helloWorld(): Observable<SummonResponse>
}
