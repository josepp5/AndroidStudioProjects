package com.example.ejerciciopag32

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.adapter_item_candy.view.*
import kotlinx.android.synthetic.main.adapter_item_candy_grid.view.*

class CandyAdapterGrid : BaseAdapter {

    var context: Context? = null
    var items = ArrayList<Candy>()

    constructor(context: Context, items: ArrayList<Candy>) {
        this.context = context
        this.items = items
    }


    override fun getCount(): Int {
        return this.items.size
    }

    override fun getItem(position: Int): Candy {
        return this.items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.adapter_item_candy_grid, p2, false)

        val item = this.items[position]
        view.textView.text = item.name
        view.imageView.setImageResource(item.image)

        return view
    }
}