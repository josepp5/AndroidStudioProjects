package com.jpoveda.mydeliciouscandiesv2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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

class CandyItemListFragment : Fragment() {
    var items: ArrayList<Candy> = ArrayList()
    lateinit var dataSource: ICandyDataSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataSource = CandyFileDataSource(requireContext())
        //dataSource = CandyMockDataSource()
        return inflater.inflate(R.layout.candy_list_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        loadView()
    }

    override fun onResume() {
        super.onResume()
        items = dataSource.getList() as ArrayList<Candy>
        if (items != null) loadView(items) else loadView()

    }

    private fun loadView(candies: List<Candy> = dataSource.getList()) {
        val adapter = RecycleAdapter(requireContext(), candies)
        {
            //goToDetail(it)
            openWeb(candies.get(it).web)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun goToDetail(position: Int) {
        val frag = CandyItemFragment(dataSource, position)

        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.viewPager, frag)
        transaction?.addToBackStack(null)

        transaction?.commit()
    }

    fun setListener(){
        btnFilter.setOnClickListener{
            if (radioFilterName.isChecked) filterByName()
            if (radioFilterSweetness.isChecked) filterBySweetness()
        }

        btnClearRadio.setOnClickListener{
            clearRadioBtn()
        }
    }

    private fun clearRadioBtn(){
        radioFilterName.isChecked = false
        radioFilterSweetness.isChecked = false
        loadView(items)
    }

    private fun filterByName() {
        val sortedItemsList = items.sortedBy { it.name }
        loadView(sortedItemsList)
    }

    private fun filterBySweetness() {
        val sortedItemsList = items.sortedBy { it.dulzor }.reversed()
        loadView(sortedItemsList)
    }

    // Abrir una pagina WEB
    private fun openWeb(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("DEBUG", "Hay un problema para encontrar un navegador.")
        }
    }

}

