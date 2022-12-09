package com.example.practicaimc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaimc.data.measurements.IMeasurementDataSource
import com.example.practicaimc.data.measurements.MeasurementFileDataSource
import kotlinx.android.synthetic.main.activity_recycler.*
/*
fun startRecyclerActivity(context: Context): Intent {
    return Intent(context, RecyclerActivity::class.java)
}

class RecyclerActivity : AppCompatActivity() {
    val dataSource: IMeasurementDataSource = MeasurementFileDataSource(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        initView()
        setListener()
    }
    private fun setListener(){
        btnVerCalculadora.setOnClickListener {
            finish()
        }
    }

    private fun initView() {
        val adapter = RecycleAdapter(this, dataSource.getList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}

 */