package com.jpoveda.mydeliciouscandiesv2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpoveda.mydeliciouscandiesv2.adapter.RecycleAdapter
import com.jpoveda.mydeliciouscandiesv2.data.candy.Candy
import com.jpoveda.mydeliciouscandiesv2.data.candy.CandyFileDataSource
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
        return inflater.inflate(R.layout.candy_list_fragment, container, false)
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
        val adapter = RecycleAdapter(requireContext(), candies,
            {
                openWeb(candies.get(it).web)
            }, {
                MakeDeleteDialog(it)
            },
            {
                favoritoClicked(it)
            },
            {
                goToDetail(it)
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun favoritoClicked(index: Int) {
        items[index].favorito = !items[index].favorito
        dataSource.saveElements(items)
        loadView(items)
    }

    private fun deleteCandy(index: Int) {
        items.removeAt(index)
        dataSource.saveElements(items)
        loadView(items)
    }

    fun setListener() {
        btnFilter.setOnClickListener {
            if (radioFilterName.isChecked) filterByName()
            if (radioFilterSweetness.isChecked) filterBySweetness()
            if (radioFilterFav.isChecked) filterByFavorites()
        }

        btnClearRadio.setOnClickListener {
            clearRadioBtn()
        }
    }

    private fun clearRadioBtn() {
        radioFilterName.isChecked = false
        radioFilterSweetness.isChecked = false
        loadView(items)
    }

    private fun filterByName() {
        val sortedItemsList = items.sortedBy { it.name }
        loadView(sortedItemsList)
    }

    private fun filterByFavorites() {
        val filteredItemsList = items.filter { it.favorito }
        loadView(filteredItemsList)
    }

    private fun filterBySweetness() {
        val sortedItemsList = items.sortedBy { it.dulzor }.reversed()
        loadView(sortedItemsList)
    }

    // Abrir una pagina WEB
    private fun openWeb(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.https) + url)
        )
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("DEBUG", getString(R.string.BrowserProblem))
        }
    }

    fun MakeDeleteDialog(index: Int) {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setMessage(getString(R.string.DeleteDialog))

            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                deleteCandy(index)

                Toast.makeText(
                    requireContext(),
                    getString(R.string.DeleteDialogPositive),
                    Toast.LENGTH_LONG
                ).show()
            }
            setNegativeButton(getString(R.string.dialog_button_negative)) { _, _ ->
                Toast.makeText(
                    requireContext(),
                    getString(R.string.DeleteDialogNegative),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        alertDialog.show()
    }

    private fun goToDetail(position: Int) {
        val frag = CandyItemFragment(position)

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.viewPager, frag)
        transaction.addToBackStack(null)

        transaction.commit()
    }
}

