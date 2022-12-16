package com.example.practicaimc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaimc.data.measurements.IMeasurementDataSource
import com.example.practicaimc.data.measurements.Measurement
import com.example.practicaimc.data.measurements.MeasurementFileDataSource
import com.example.practicaimc.utils.CalendarHelper
import com.example.practicaimc.utils.IMCCalculator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calculadora_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }

    fun setView(){
         // Se crea el adapter.
         val adapter = ViewPagerAdapter(supportFragmentManager)

         // Se añaden los fragments y los títulos de pestañas.
         adapter.addFragment(calculadoraTab(), "Calculadora")
         adapter.addFragment(HistoricoTab(), "Historico")

         // Se asocia el adapter.
         viewPager.adapter = adapter

         // Se cargan las tabs.
         tabLayout.setupWithViewPager(viewPager)
    }
}
