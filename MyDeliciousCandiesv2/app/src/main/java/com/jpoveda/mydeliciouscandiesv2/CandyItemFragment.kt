package com.jpoveda.mydeliciouscandiesv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.candy_item_activity.*

class CandyItemFragment(val position: Int) : Fragment() {
    var items: ArrayList<Candy> = ArrayList()
    lateinit var dataSource: ICandyDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataSource = CandyFileDataSource(requireContext())
        var view = inflater.inflate(R.layout.candy_item_activity, container, false)

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        items = dataSource.getList() as ArrayList<Candy>
        editTextNombre.hint = items[position].name
        txtFabricanteInfo.hint = items[position].fabricante
        txtFormatoInfo.hint = items[position].formato
        txtTipoInfo.hint = items[position].tipo
        editTextDulzor.hint = items[position].dulzor.toString()
        imgCandyB.setImageBitmap(items[position].image)
    }

    override fun onResume() {
        super.onResume()
        items = dataSource.getList() as ArrayList<Candy>
    }
}