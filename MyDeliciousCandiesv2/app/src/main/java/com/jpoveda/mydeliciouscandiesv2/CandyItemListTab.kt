package com.jpoveda.mydeliciouscandiesv2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpoveda.mydeliciouscandiesv2.adapter.RecycleAdapter
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyMockDataSource
import com.jpoveda.mydeliciouscandiesv2.data.candy.ICandyDataSource
import kotlinx.android.synthetic.main.candy_list_fragment.*

class CandyItemListTab : Fragment() {
    var items: List<Candy> = ArrayList()
    lateinit var dataSource: ICandyDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataSource = CandyMockDataSource()
        items = dataSource.getList()
        return inflater.inflate(R.layout.candy_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        loadView()
    }

    private fun loadView(candies: List<Candy> = dataSource.getList()) {
        val adapter = RecycleAdapter(requireContext(), candies)
        {
            val frag = CandyItemFragment(dataSource, it)

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.viewPager, frag)
            transaction?.addToBackStack(null)

            transaction?.commit()
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    fun setListener(){
        btnFilter.setOnClickListener{
            if (radioFilterName.isChecked) filterByName()
            if (radioFilterSweetness.isChecked) filterBySweetness()
        }
    }

    private fun filterByName() {
        val sortedItemsList = items.sortedBy { it.name }
        loadView(sortedItemsList)
    }

    private fun filterBySweetness() {
        val sortedItemsList = items.sortedBy { it.dulzor }.reversed()
        loadView(sortedItemsList)
    }
}

