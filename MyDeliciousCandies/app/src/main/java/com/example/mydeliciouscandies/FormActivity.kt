package com.example.mydeliciouscandies

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.get
import kotlinx.android.synthetic.main.candy_item_activity.*
import kotlinx.android.synthetic.main.form_activity.*



class FormActivity : AppCompatActivity() {
    val items = ArrayList<Candy>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)

        setListener()
    }

    fun CrearCandy() {
        val rb = if (rbMasticable.isChecked) "Masticable" else if(rbChicle.isChecked) "Chicle" else "No se ha seleccionado tipo"
        val rb2 = if (rbIndvidual.isChecked) "Individual" else if (rbBolsa.isChecked) "Bolsa" else "No se ha seleccionado formato"
        items.add(
            Candy(
                editTxtName.text.toString(),
                editTxtFabricante.text.toString(),
                Integer.parseInt(editTxtDulzor.text.toString()),
                rb,
                rb2,
                editTxtWeb.text.toString()
            )
        )
        Toast.makeText(applicationContext, "Golosina creada correctamente", Toast.LENGTH_SHORT)
            .show()
    }

    private fun setListener() {
        btnCrear.setOnClickListener() {
            CrearCandy()
        }

        btnVerLista.setOnClickListener {
            // Se crea un objeto de tipo Intent.
            val myIntent = StartMainActivity(this)

            myIntent.putParcelableArrayListExtra("candies_list", items)
            startActivity(myIntent)
        }

        btnAddPicA.setOnClickListener {
            CameraForResult()
        }
    }
    private fun CameraForResult(){
        val intent = Intent(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        // startActivityForResult(intent,123)
        PermissionManager().checkPermissionForResult(this,
            Manifest.permission.CAMERA,
            1245,
            intent,
            123,
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            123 -> {
                if (resultCode== Activity.RESULT_OK){
                    imgCandyA?.setImageBitmap(data?.getParcelableExtra("data"))
                }
            }
        }
    }
}

fun StartFormActivity(context: Context): Intent {
    return Intent(context, FormActivity::class.java)
}

