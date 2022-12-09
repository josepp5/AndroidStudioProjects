package com.example.clase1010

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

        // var txtName: TextView = findViewById(R.id.txtName)
        txtName.setText(R.string.hello)

        button.setOnClickListener {
            actionButtons("button")
        }

        buttonToast.setOnClickListener {
            actionButtons("snackbar")
        }
    }

    private fun actionButtons(cadena: String) {
        when (cadena) {
            "button" -> {
                checkBox.isChecked = !checkBox.isChecked

                if (radioButton.isChecked) {
                    radioButton2.isChecked = true
                } else {
                    radioButton.isChecked = true
                }
                toggleButton.isChecked = !toggleButton.isChecked
                switch1.isChecked = !switch1.isChecked
            }

            "toast" -> {
                var message: String = ""
                if (!editText.text.isEmpty()) {
                    message += "Esto es del PlainText ${editText.text}"
                }
                message += "Esto es del TextView ${txtName.text}."
                Toast.makeText(applicationContext, "Holaaa $message", Toast.LENGTH_SHORT).show()
            }

            "snackbar" -> {
                Snackbar.make(root_main, "snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Aceptar") {txtName.text = "Has aceptado el snackbar"
                    }
                    .show()
            }
        }
    }
}