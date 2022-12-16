package com.jpoveda.mydeliciouscandiesv2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jpoveda.mydeliciouscandiesv2.R
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import kotlinx.android.synthetic.main.candy_item_adapter.view.*

class RecycleAdapter(context: Context, items: List<Candy>,
                     listener: ((position: Int) -> Unit),
                     listenerDeleteClicked: ((position: Int) -> Unit),
                     listenerFavoritoClicked: ((position: Int) -> Unit),
                     listenerModifyClicked: ((position: Int) -> Unit)) :
    RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    var context: Context? = null
    var items: List<Candy>
    val listener: ((position: Int) -> Unit)
    val listenerDeleteClicked: ((position: Int) -> Unit)
    val listenerFavoritoClicked: ((position: Int) -> Unit)
    val listenerModifyClicked: ((position: Int) -> Unit)

    init {
        this.context = context
        this.items = items
        this.listener = listener
        this.listenerDeleteClicked = listenerDeleteClicked
        this.listenerFavoritoClicked = listenerFavoritoClicked
        this.listenerModifyClicked = listenerModifyClicked
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.txtName
        val fabricanteView: TextView = view.txtFabricante
        val dulzorView: ProgressBar = view.progressBar2
        val tipoView: TextView = view.txtTipo
        val webView: TextView = view.txtWeb
        val imageView : ImageView = view.imageView
        val btnDeleteView : Button = view.btnDelete
        val favoritoView : CheckBox = view.checkboxFav
        val btnModifyView : Button = view.btnModify
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameView.text = item.name
        holder.fabricanteView.text = item.fabricante
        holder.dulzorView.progress = item.dulzor
        holder.tipoView.text = item.tipo
        holder.webView.text = item.web
        holder.imageView.setImageBitmap(item.image)
        holder.favoritoView.isChecked = item.favorito

        holder.webView.setOnClickListener(View.OnClickListener { listener.invoke(position) })
        holder.favoritoView.setOnClickListener(View.OnClickListener { listenerFavoritoClicked.invoke(position) })
        holder.btnDeleteView.setOnClickListener(View.OnClickListener { listenerDeleteClicked.invoke(position) })
        holder.btnModifyView.setOnClickListener(View.OnClickListener { listenerModifyClicked.invoke(position) })
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