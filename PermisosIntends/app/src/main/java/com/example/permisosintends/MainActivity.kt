package com.example.permisosintends

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.app.Activity
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.core.app.ActivityCompat


/// 1 - check Permision
/// 2 - Si es SI lanzo intent, Si es NO, pido al usuario request permission (lanza pop up preguntando)
/// 3 -

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {

        /// Abre una web en el navegador al hacer click en el boton
        btnWeb.setOnClickListener {
            openWeb("https://www.javiercarrasco.es")
        }
        btnPhone.setOnClickListener {
            call("tel:600020548")
        }
        btnCamera.setOnClickListener {
            camera()
        }

        btnSms.setOnClickListener{
            sms()
        }

        btnEmail.setOnClickListener {
            email()
        }

        btnCameraForResult.setOnClickListener {
            CameraForResult()
        }
    }

    /// Abre la camara y al hacer una foto devuelve el resultado
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

    // Enviar email
    private fun email(){
        val TO = arrayOf("javier@javiercarrasco.es")
        val CC = arrayOf("")
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/html" // o también text/plain
        intent.putExtra(Intent.EXTRA_EMAIL, TO)
        intent.putExtra(Intent.EXTRA_CC, CC)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Envío de un email desde Kotlin")
        intent.putExtra(Intent.EXTRA_TEXT, "Esta es mi prueba de envío de un correo.")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar correo..."))
        }
    }

    // Enviar SMS
    private fun sms(){
        val intent = Intent(
             Intent.ACTION_SENDTO,
             Uri.parse("smsto:" + 777666777)
             )

         intent.putExtra("sms_body", "Cuerpo del mensaje")

         if (intent.resolveActivity(packageManager) != null) {
             startActivity(intent)
             }
    }

    //  Abrir Camara
    private fun camera() {
        val intent = Intent(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        startActivity(intent)

        val pm = PermissionManager()
        pm.checkPermission(this, Manifest.permission.CAMERA, 4321, intent)
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

    private fun call(tlf: String) {
        val callIntent = Intent(
            Intent.ACTION_CALL,
            Uri.parse(tlf)
        )
        startActivity(callIntent)
        // Para utilizar la funcion magica..
        val pm = PermissionManager()
        pm.checkPermission(this, Manifest.permission.CALL_PHONE, 1234, callIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1234 -> {
                if (grantResults.isEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d("DEBUG", "Permiso concedido!!")
                    val callIntent = Intent(
                        Intent.ACTION_CALL,
                        Uri.parse("tel:600020548")
                    )
                    startActivity(callIntent)
                } else {
                    Log.d("DEBUG", "Permiso denegado!!")
                }
            }

            4321 -> {
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("DEBUG", "Permiso condedido!!")
                    val intent = Intent(
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    )
                    startActivity(intent)
                } else {
                    Log.d("DEBUG", "Permiso cancelado!!")
                }
            }
            1245 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d("DEBUG", "Permiso concedido!!")
                    val intent = Intent(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                    startActivityForResult(intent,123)
                } else {
                    Log.d("DEBUG", "Permiso denegado!!")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            123 -> {
                if (resultCode==Activity.RESULT_OK){
                    imageView?.setImageBitmap(data?.getParcelableExtra("data"))
                }
            }
        }
    }
}
