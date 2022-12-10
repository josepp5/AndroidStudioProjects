package com.jpoveda.mydeliciouscandiesv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.candy_item_activity.view.*

class CandyItemFragment(val dataSource: ICandyDataSource, val position: Int) : Fragment() {
    var items: List<Candy> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        items = dataSource.getList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.candy_item_activity, container, false)
        view.txtNombre.setText(items[position].name)
        view.txtFabricanteInfo.setText(items[position].name)
        view.txtFormatoInfo.setText(items[position].name)
        view.txtTipoInfo.setText(items[position].name)
        //view.progressBar3.progress(items[position].dulzor)
        return  view
    }
}