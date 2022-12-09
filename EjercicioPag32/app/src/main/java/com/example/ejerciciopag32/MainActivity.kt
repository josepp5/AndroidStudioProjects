package com.example.ejerciciopag32

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapterCandy : CandyAdapterGrid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setListeners()
    }

    private fun initViews(){
        val items = ArrayList<Candy>()
        items.add(Candy("caramelos",  R.drawable.ic_launcher_foreground))
        items.add(Candy("chupa-chups",  R.mipmap.ic_launcher))
        items.add(Candy("corazones",  R.drawable.ic_launcher_foreground))
        items.add(Candy("pirufrutas",  R.mipmap.ic_launcher))
        items.add(Candy("rosquillas",  R.drawable.ic_launcher_foreground))
        items.add(Candy("frutimelos",  R.mipmap.ic_launcher))
        items.add(Candy("ositos",  R.mipmap.ic_launcher))
        items.add(Candy("habichuelas",  R.mipmap.ic_launcher))
        items.add(Candy("lacasitos",  R.mipmap.ic_launcher))

        adapterCandy = CandyAdapterGrid(this,items)
        gridView.adapter = adapterCandy
    }

    private fun setListeners(){
        gridView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->

                val item = adapterCandy.getItem(position)
                Toast.makeText(this, "Has hecho click en ${item.name}", Toast.LENGTH_LONG)
                    .show()
                Log.d("MainActivity","Has hecho click en ${item.name}")
            }
    }

}
