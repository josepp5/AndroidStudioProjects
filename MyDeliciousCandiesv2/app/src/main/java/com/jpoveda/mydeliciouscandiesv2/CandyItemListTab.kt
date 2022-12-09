package com.jpoveda.mydeliciouscandiesv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpoveda.mydeliciouscandiesv2.adapter.OnItemClickListener
import com.jpoveda.mydeliciouscandiesv2.adapter.RecycleAdapter
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.candy_list_fragment.*

class CandyItemListTab : Fragment() {

    var items = ArrayList<Candy>()
    lateinit var dataSource: ICandyDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.candy_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSource = CandyFileDataSource(requireContext())
        initView()
    }

    private fun initView() {
        val adapter = RecycleAdapter(requireContext(), dataSource.getList())
        {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}

