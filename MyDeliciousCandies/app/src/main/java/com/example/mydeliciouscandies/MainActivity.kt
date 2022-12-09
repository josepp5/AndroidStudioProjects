package com.example.mydeliciouscandies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.form_activity.view.*
import kotlinx.android.synthetic.main.candy_item_activity.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CandyAdapter
    var items = ArrayList<Candy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val candies = intent.extras?.getParcelableArrayList<Candy>("candies_list")
        if (candies != null) {
            items = candies
        }
        initViews()
        setListener()
    }

    private fun initViews() {

        items.add(Candy("lenguas", "fini", 2, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("caramelos", "kojac", 4, "Gominola", "Individual", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("chupa-chups", "kojac", 1, "Masticable", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("corazones", "fini", 5, "Masticable", "Individual", ""))
        items.add(Candy("pirufrutas", "kojac", 4, "Chicle", "Bolsa", ""))
        items.add(Candy("rosquillas", "fini", 2, "Gominola", "Bolsa", ""))
        items.add(Candy("frutimelos", "kojac", 1, "Masticable", "Individual", ""))
        items.add(Candy("ositos", "kojac", 5, "Chicle", "Bolsa", ""))
        items.add(Candy("habichuelas", "fini", 4, "Gominola", "Individual", ""))
        items.add(Candy("lacasitos", "kojac", 2, "Masticable", "Bolsa", ""))

        adapter = CandyAdapter(this, items)
        listCandy.adapter = adapter
    }

    private fun setListener() {
        btnVerForm.setOnClickListener {
            // Se crea un objeto de tipo Intent.
            val myIntent = StartFormActivity(this)
            startActivity(myIntent)
        }

        /*
        listCandy.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->

            val item = adapter.getItem(position)
                val myIntent = StartItemActivity(this)
                startActivity(myIntent)
        }

        listCandy.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->

                val item = adapter.getItem(position)
                Toast.makeText(this, "Has hecho click en ${items[position].name}", Toast.LENGTH_LONG)
                    .show()
            //    Log.d("MainActivity","Has hecho click en ${items[position].name}")
            }

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, l ->
                val item = adapter?.getItem(position)?.title
                Toast.makeText(this, "Has hecho click en ${adapter?.getItem(position)?.title}", Toast.LENGTH_LONG)
                    .show()
            }*/
    }
}

fun StarItemActivity(context: Context): Intent {
    return Intent(context, ItemActivity::class.java)
}

fun StartMainActivity(context: Context): Intent {
    return Intent(context, MainActivity::class.java)
}


