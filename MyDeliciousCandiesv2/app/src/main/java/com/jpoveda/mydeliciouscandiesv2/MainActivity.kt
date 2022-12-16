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
        adapter.addFragment(CandyForm(), "CandyForm")
        //adapter.addFragment(CandyItemFragment(),"CandyItem")

        // Se asocia el adapter.
        viewPager.adapter = adapter

        // Se cargan las tabs.
        tabLayout.setupWithViewPager(viewPager)
    }

    /*
     private fun showItemFragment() {
         val fragment = CandyItemActivity(dataSource,)
         val bundle = Bundle()
         bundle.putInt("position", 1)
         fragment.arguments = bundle
         val transaction = supportFragmentManager.beginTransaction()

         transaction.replace(R.id.viewPager, fragment)
         transaction.addToBackStack(null)
         transaction.commit()
         }
     */
}