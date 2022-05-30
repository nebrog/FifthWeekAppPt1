package com.example.fifthweekapp.data.api

import android.util.Log
import com.example.fifthweekapp.data.model.HeroesItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.*
import java.io.IOException

object DotaAPI {
    val URL = "https://api.opendota.com/api/constants/heroes"
    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val moshi = Moshi.Builder().build()

    @ExperimentalStdlibApi
    private val gistJsonAdapter = moshi.adapter<Map<String, HeroesItem>>()


    @ExperimentalStdlibApi
    fun loadData(result:HerCallback){


        val request: Request = Request.Builder().url(URL).build()
        Log.v("Nebrog","E")

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("nebrog", e.message.toString())
                result.onFail()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.v("Nebrog","F")

                val json = response.body?.string()
                val gist = gistJsonAdapter.fromJson(json)
                if(gist == null){
                    result.onFail()
                }else{
                    result.onSuccess(gist)
                }
                Log.v("nebrog", gist.toString())
                Log.v("Nebrog","G")
            }

        })
        Log.v("Nebrog","H")


    }
    interface HerCallback{
        fun onSuccess(map:Map<String,HeroesItem>)
        fun onFail()
    }
}




