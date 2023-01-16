package com.jpoveda.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     private val personas =
         arrayOf(
                 "Javier", "Pedro", "Nacho", "Patricia",
     "Miguel", "Susana", "Raquel", "Antonio", "Andrea",
     "Nicolás", "Juan José", "José Antonio", "Daniela",
     "María", "Verónica"
     )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        }

    private fun setView(){
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                personas
            )
        val listView: ListView = lista
        listView.adapter = arrayAdapter

        registerForContextMenu(listView)

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Pulsado $id - " +
                    "${listView.getItemAtPosition(position)}", Toast.LENGTH_LONG).show()
             }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
         ) {
         super.onCreateContextMenu(menu, v, menuInfo)
         val inflater = menuInflater
         inflater.inflate(R.menu.list_menu, menu)
         }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Se obtiene el nombre de la persona, con
        // ApaterView.AdapterContextMenuInfo se obtiene la posición
        // sobre la que se ha hecho clic.
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        val nombre = personas[posicion]
        return when (item.itemId) {
            R.id.option01 -> {
                myToast("Opción 1: $nombre")
                true
            }
            R.id.option02 -> {
                myToast("Opción 2: $nombre")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflate = menuInflater
            inflate.inflate(R.menu.demo_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.op01 -> {
                    Log.d("MENU", "${getString(R.string.op1)} seleccionada")
                    Toast.makeText(
                        this,
                        "${getString(R.string.op1)} seleccionada",
                        Toast.LENGTH_LONG
                    ).show()
                    true
                }
                R.id.op02 -> { // Esta opción no haría falta, ya que abre un submenú.
                    Log.d("MENU", "${getString(R.string.op2)} seleccionada")
                    Toast.makeText(
                        this,
                        "${getString(R.string.op2)} seleccionada",
                        Toast.LENGTH_LONG
                    ).show()
                    true
                }

                R.id.op021 -> {
                    Log.d("MENU", "${getString(R.string.op21)} seleccionada")
                    Toast.makeText(
                        this,
                        "${getString(R.string.op3)} seleccionada",
                        Toast.LENGTH_LONG
                    ).show()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }


        private fun myToast(mensaje: String) {
            Toast.makeText(
                this,
                mensaje,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
