package com.example.practicaimc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaimc.data.measurements.IMeasurementDataSource
import com.example.practicaimc.data.measurements.Measurement
import com.example.practicaimc.data.measurements.MeasurementFileDataSource
import kotlinx.android.synthetic.main.activity_recycler.*

class HistoricoTab : Fragment() {
    var items = ArrayList<Measurement>()
    lateinit var dataSource: IMeasurementDataSource
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_recycler,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataSource = MeasurementFileDataSource(requireContext())
    }

    override fun onResume() {
        super.onResume()
        loadHistorico()
    }

    fun loadHistorico() {
        val adapter = RecycleAdapter(requireContext(), dataSource.getList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}