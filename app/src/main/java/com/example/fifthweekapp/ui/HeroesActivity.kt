package com.example.fifthweekapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fifthweekapp.R
import com.example.fifthweekapp.data.api.DotaAPI
import com.example.fifthweekapp.data.model.HeroesItem
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity() {

    private val adapter = HeroesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        val her = HeroNike()
        DotaAPI.loadData(her)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

    }

    inner class HeroNike:DotaAPI.HerCallback{
        override fun onSuccess(map: Map<String, HeroesItem>) {
            runOnUiThread { adapter.setData(map) }

        }

        override fun onFail() {
        }
    }
}