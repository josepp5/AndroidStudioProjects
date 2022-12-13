package com.example.practicaimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practicaimc.data.measurements.IMeasurementDataSource
import com.example.practicaimc.data.measurements.Measurement
import com.example.practicaimc.data.measurements.MeasurementFileDataSource
import com.example.practicaimc.utils.CalendarHelper
import com.example.practicaimc.utils.FileManager
import com.example.practicaimc.utils.IMCCalculator
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var items: ArrayList<Measurement> = ArrayList()
    val dataSource: IMeasurementDataSource = MeasurementFileDataSource(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
        items = dataSource.getList() as ArrayList<Measurement>
    }

    // Aqui inicio las vistas
    private fun setListener() {
        // Evento click del boton calcular, muestra toast si falta algun campo por rellenar
        btnCalcular.setOnClickListener {
            CalculateIsClicked()
        }
        // Evento click del boton reset que vacia los campos y radiobutton (utilizado para test)
        btnReset.setOnClickListener{
            editTxtPeso.text = null
            editTxtAltura.text = null
            rbHombre.isChecked = false
            rbMujer.isChecked = false
            txtResultado.text = null
            txtCategoria.text = null
        }
        btnHistorico.setOnClickListener {
            val myIntent = startRecyclerActivity(this)
            startActivity(myIntent)
        }
    }

    // Funcion que comprueba que alguno de los RadioButtons este activado
    private fun ComprobarRb():Boolean{
        return rbMujer.isChecked || rbHombre.isChecked
    }

    // Esta funcion hace el calculo del IMC que sera mostrado
    private fun CalculateIsClicked() {
        if (editTxtAltura.text.isEmpty() || editTxtPeso.text.isEmpty() || !ComprobarRb()) {
            Toast.makeText(
                applicationContext,
                "Los campos no deben estar vacios para poder realizar la operacion",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val peso = editTxtPeso.text.toString().toDouble()
            val altura = editTxtAltura.text.toString().toDouble()
            val sexo = if (rbHombre.isChecked) "Hombre" else "Mujer"
            val resultado = IMCCalculator.Calculate(altura, peso)
            val rango = IMCCalculator.Rango(this, resultado, rbHombre.isChecked)
            txtCategoria.text = rango
            val date = CalendarHelper.getFormattedDate()
            txtResultado.text = String.format("%.2f", resultado)
            val measurement = Measurement(date,sexo,resultado,rango)

            items.add(measurement)
            dataSource.saveElement(items)
        }
    }
}