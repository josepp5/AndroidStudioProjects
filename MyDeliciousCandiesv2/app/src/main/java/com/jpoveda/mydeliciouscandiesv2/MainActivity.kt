package com.jpoveda.mydeliciouscandiesv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jpoveda.mydeliciouscandiesv2.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }
    fun setView(){
        // Se crea el adapter.
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // Se añaden los fragments y los títulos de pestañas.
        adapter.addFragment(CandyItemListTab(), "CandyItemList")
        adapter.addFragment(CandyForm(), "CandyForm")

        // Se asocia el adapter.
        viewPager.adapter = adapter

        // Se cargan las tabs.
        tabLayout.setupWithViewPager(viewPager)
    }
}