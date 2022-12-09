package com.example.practicaimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }
    // Aqui inicio las vistas
    private fun initViews() {
        // Evento click del boton calcular, muestra toast si falta algun campo por rellenar
        btnCalcular.setOnClickListener {
            if (editTxtAltura.text.isEmpty() || editTxtPeso.text.isEmpty() || !ComprobarRb()) {
                Toast.makeText(
                    applicationContext,
                    "Los campos no deben estar vacios para poder realizar la operacion",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Calculo()
            }
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
    }

    // Funcion que comprueba que alguno de los RadioButtons este activado
    private fun ComprobarRb():Boolean{
        return rbMujer.isChecked || rbHombre.isChecked
    }

    // Esta funcion hace el calculo del IMC que sera mostrado
    private fun Calculo() {
        val peso = editTxtPeso.text.toString().toDouble()
        val altura = editTxtAltura.text.toString().toDouble()
        val resultado: Double = peso / (altura * altura)

        txtResultado.text = String.format("%.2f", resultado)

        Rango(resultado)
    }

    // Mostrara el rango en el que se encuentra la persona segun su resultado y caracteristicas
    private fun Rango(result: Double) {
        if (rbHombre.isChecked) {
            if (result < 18.5) txtCategoria.text = getString(R.string.peso_inferior)
            else if (result < 24.9) txtCategoria.text = getString(R.string.peso_normal)
            else if (result < 29.9) txtCategoria.text = getString(R.string.sobrepeso)
            else if (result > 30.0) txtCategoria.text = getString(R.string.obesidad)
        } else if (rbMujer.isChecked) {
            if (result < 18.5) txtCategoria.text = getString(R.string.peso_inferior)
            else if (result < 23.9) txtCategoria.text = getString(R.string.peso_normal)
            else if (result < 28.9) txtCategoria.text = getString(R.string.sobrepeso)
            else if (result > 29.0) txtCategoria.text = getString(R.string.obesidad)
        }
    }
}