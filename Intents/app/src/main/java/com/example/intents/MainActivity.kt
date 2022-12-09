package com.example.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }

    private fun setListener(){
        button.setOnClickListener {
            // Se crea un objeto de tipo Intent.
             val myIntent = StartSecondActivity(this)

             // Se lanza la activity.
             startActivity(myIntent)
        }
    }
}