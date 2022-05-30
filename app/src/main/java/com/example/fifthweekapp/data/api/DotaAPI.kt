package com.example.fifthweekapp.data.api

import android.util.Log
import com.example.fifthweekapp.data.model.HeroesItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.*
import java.io.IOException

object DotaAPI {
    const val URL_IMG = "https://api.opendota.com"
    val URL = "https://api.opendota.com/api/constants/heroes"
    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val moshi = Moshi.Builder().build()

    @ExperimentalStdlibApi
    private val gistJsonAdapter = moshi.adapter<Map<String, HeroesItem>>()


    @ExperimentalStdlibApi
    fun loadData(result:HeroCallback){


        val request: Request = Request.Builder().url(URL).build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("nebrog", e.message.toString())
                result.onFail()
            }

            override fun onResponse(call: Call, response: Response) {

                val json = response.body?.string()
                val gist = gistJsonAdapter.fromJson(json)
                if(gist == null){
                    result.onFail()
                }else{
                    result.onSuccess(gist)
                }
                Log.v("nebrog", gist.toString())
            }

        })


    }
    interface HeroCallback{
        fun onSuccess(map:Map<String,HeroesItem>)
        fun onFail()
    }
}




