package com.example.fifthweekapp.ui.Heroes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fifthweekapp.R
import com.example.fifthweekapp.data.api.DotaAPI
import com.example.fifthweekapp.data.model.HeroesItem
import com.example.fifthweekapp.ui.Hero.HeroActivity
import com.example.fifthweekapp.ui.Hero.HeroActivity.Companion.HERO_ARG

@ExperimentalStdlibApi
class HeroesActivity : AppCompatActivity(), OnHeroClickListener {

    private val adapter = HeroesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heroes)
        supportActionBar?.hide()
        val heroTake = HeroTake()
        DotaAPI.loadData(heroTake)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

    }

    inner class HeroTake : DotaAPI.HeroCallback {
        override fun onSuccess(map: Map<String, HeroesItem>) {
            runOnUiThread { adapter.setData(map) }
        }
        override fun onFail() {
        }
    }

    override fun onClick(item: HeroesItem) {
        val intent = Intent(this, HeroActivity::class.java)
        intent.putExtra(HERO_ARG, item)
        startActivity(intent)

    }
}