package com.jpoveda.mydeliciouscandiesv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jpoveda.mydeliciouscandiesv2.adapter.ViewPagerAdapter
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dataSource: ICandyDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    fun setView(){
        // Se crea el adapter.
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // Se añaden los fragments y los títulos de pestañas.
        adapter.addFragment(CandyItemListFragment(), "CandyItemList")
        // Formulario
        adapter.addFragment(CandyForm(), "CandyForm")
        // En este queria hacer el modificar pero no lo llegue a conseguir
        //adapter.addFragment(CandyItemFragment(0),"CandyItem")

        // Se asocia el adapter.
        viewPager.adapter = adapter

        // Se cargan las tabs.
        tabLayout.setupWithViewPager(viewPager)
    }
}