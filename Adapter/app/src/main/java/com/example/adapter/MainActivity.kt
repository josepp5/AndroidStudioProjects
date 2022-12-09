package com.example.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val nombres = arrayOf(
        "Javier", "Nacho", "Patricia", "Miguel", "Susana", "Rosa", "Juan",
        "Pedro", "Asuncion", "Antonio", "Lorena", "Veronica", "Paola",
        "Esteban", "Andrea", "Maria"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /// val adapter = ArrayAdapter(this, R.layout.listview_item, nombres)
        val items = ArrayList<Persona>()
        items.add(Persona("Jose", R.mipmap.ic_launcher))
        items.add(Persona("Pepe", R.mipmap.ic_launcher))
        items.add(Persona("Manuel", R.mipmap.ic_launcher))
        items.add(Persona("Lucia", R.mipmap.ic_launcher))
        val adapter = PersonaAdapter(this, items)

        myGridView.adapter = adapter
        myGridView.setOnItemClickListener {
            adapterView, view, i ,l ->
            Toast.makeText(applicationContext,adapter.getItem(i), Toast.LENGTH_SHORT).show()
        }
    }
}
