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
        //setListener()
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
/*
    // Aqui inicio las vistas
    private fun setListener() {
        // Evento click del boton calcular, muestra toast si falta algun campo por rellenar
        btnCalcular.setOnClickListener {
            CalculateIsClicked()
        }
        // Evento click del boton reset que vacia los campos y radiobutton (utilizado para test)
        btnReset.setOnClickListener {
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
    private fun ComprobarRb(): Boolean {
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
            val measurement = Measurement(date, sexo, resultado, rango, peso, altura)
            MakeDialog(measurement)
            //items.add(measurement)
            //dataSource.saveElement(items)
        }
    }

    fun MakeDialog(measurement: Measurement) {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setMessage(getString(R.string.dialog_message))

            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                items.add(measurement)
                dataSource.saveElement(items)

                Toast.makeText(
                    this@MainActivity,
                    "La informacion ha quedado registrada en el historico",
                    Toast.LENGTH_LONG
                ).show()
            }
            setNegativeButton(getString(R.string.dialog_button_negative)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    "La informacion no ha quedado registrada",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        alertDialog.show()
    }

 */
}
