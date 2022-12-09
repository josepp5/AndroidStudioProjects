package com.example.mydeliciouscandies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.candy_item_adapter.view.*
import kotlinx.android.synthetic.main.form_activity.*
import kotlinx.android.synthetic.main.activity_main.*


class CandyAdapter :BaseAdapter {
    var context: Context? = null
    var items = ArrayList<Candy>()

    constructor(context: Context, items: ArrayList<Candy>) {
        this.context = context
        this.items = items
    }
    override fun getCount(): Int {
        return this.items.size
    }

    override fun getItem(position: Int): Any {
        return this.items[position]
    }

    override fun getItemId(position: Int): Long {
        return 10
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val item = this.items[position]

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.candy_item_adapter, p2, false)

        view.name.text = item.name
        view.progressBar2.progress = item.dulzor
        view.txtTipo.text = "Tipo: " +  item.tipo
      //  view.imageView.setImageResource(item.image)
        view.txtFabricante.text = "Fabricante: " + item.fabricante

        return view
    }
}