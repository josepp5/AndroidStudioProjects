package com.example.practicaimc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaimc.data.measurements.Measurement
import kotlinx.android.synthetic.main.measurement_item_adapter.view.*

class RecycleAdapter(context: Context, items: List<Measurement>) :
    RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    var context: Context? = null
    var items: List<Measurement>

    init {
        this.context = context
        this.items = items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.sexoView.text = item.sexo
        holder.rangoView.text = item.rango
        holder.resultView.text = String.format("%.2f", item.result)
        holder.dateView.text = item.date
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.measurement_item_adapter, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sexoView: TextView = view.txtSexo
        val rangoView: TextView = view.txtRango
        val resultView: TextView = view.txtResult
        val dateView: TextView = view.txtDate
    }
}