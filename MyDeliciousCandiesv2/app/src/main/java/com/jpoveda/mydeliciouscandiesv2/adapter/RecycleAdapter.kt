package com.jpoveda.mydeliciouscandiesv2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jpoveda.mydeliciouscandiesv2.R
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import kotlinx.android.synthetic.main.candy_item_adapter.view.*


interface OnItemClickListener {
    fun onItemClick(item: Candy)
}

class RecycleAdapter(context: Context, items: List<Candy>, listener: ((item: Candy) -> Unit)) :
    RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    var context: Context? = null
    var items: List<Candy>
    val listener: ((item: Candy) -> Unit)

    init {
        this.context = context
        this.items = items
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.txtName
        val fabricanteView: TextView = view.txtFabricante
        val dulzorView: ProgressBar = view.progressBar2
        val tipoView: TextView = view.txtTipo


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameView.text = item.name
        holder.fabricanteView.text = item.fabricante
        holder.dulzorView.progress = item.dulzor
        holder.tipoView.text = item.tipo

        holder.itemView.setOnClickListener(View.OnClickListener { listener.invoke(item) })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.candy_item_adapter, parent, false)

        return ViewHolder(view)
    }
}