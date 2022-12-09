package com.example.clase3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var rbtnHello: RadioButton
    lateinit var rbtnBye: RadioButton
    lateinit var switchUno: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        setListeners()
    }

    fun findViews() {
        btn = findViewById(R.id.btnMainOk)
        rbtnHello = findViewById(R.id.radioButtonSaludar)
        rbtnBye = findViewById(R.id.radioButtonDespedirse)
        switchUno = findViewById(R.id.switch1)
    }

    fun setListeners() {
        btn.setOnClickListener {
            sayHello()
        }
        switchUno.setOnCheckedChangeListener { compundButton, b ->
            btn.isEnabled = b
        }
    }

    fun sayHello() {
        if (rbtnHello.isChecked) {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        } else if (rbtnBye.isChecked) {
            Toast.makeText(this, "Bye Bye", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show()
        }
    }
}