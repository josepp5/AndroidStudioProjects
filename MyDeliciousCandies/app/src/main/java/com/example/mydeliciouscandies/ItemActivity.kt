package com.example.mydeliciouscandies

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.form_activity.*
import kotlinx.android.synthetic.main.candy_item_activity.*


class ItemActivity : AppCompatActivity() {
    var items = ArrayList<Candy>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.candy_item_activity)

        val candies = intent.extras?.getParcelableArrayList<Candy>("candies_list")
        if (candies != null) {
            items = candies
        }
        setListener()
    }


    private fun setListener() {
        btnVerLista.setOnClickListener {
            // Se crea un objeto de tipo Intent.
            val myIntent = StartItemListActivity(this)
            myIntent.putParcelableArrayListExtra("candies_list", items)

            startActivity(myIntent)
        }
        btnAddPicB.setOnClickListener {
            CameraForResult()
        }

        txtWebInfo.setOnClickListener{
            openWeb(txtWebInfo.toString())
        }
    }

    // Abrir una pagina WEB
    private fun openWeb(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("DEBUG", "Hay un problema para encontrar un navegador.")
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
                if (resultCode == Activity.RESULT_OK){
                    imgCandyB?.setImageBitmap(data?.getParcelableExtra("data"))
                }
            }
        }
    }
}


fun StartItemListActivity(context: Context): Intent {
    return Intent(context, MainActivity::class.java)
}