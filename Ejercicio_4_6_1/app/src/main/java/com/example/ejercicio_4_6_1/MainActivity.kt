package com.example.ejercicio_4_6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botonAceptar.setOnClickListener {
            actionButton();
        }

    }

    private fun actionButton() {
        if (editTextNombre.text.isEmpty()) {
            Toast.makeText(applicationContext, "Rellena el campo del Nombre", Toast.LENGTH_SHORT).show()
        } else if (switch1.isChecked){
            Snackbar.make(root_main, "${editTextNombre.text}", Snackbar.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, "${editTextNombre.text}", Toast.LENGTH_SHORT).show()
        }
    }
}