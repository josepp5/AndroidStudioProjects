package com.example.intends2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.intends2.SecondActivity.Companion.RETURN_MESSAGE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        var NAME_MESSAGE = "name"
        var LASTNAME_MESSAGE = "lastname"
        var AGE_MESSAGE = "age"
        var REQUEST_CODE_SECOND_ACTIVITY = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        btnSecond.setOnClickListener {
            /*
            val myIntent = Intent(this, SecondActivity::class.java)
            myIntent.putExtra(NAME_MESSAGE, "Jose Maria")
            myIntent.putExtra(LASTNAME_MESSAGE, "Company")
            myIntent.putExtra(AGE_MESSAGE, 39)

            //startActivity(myIntent)
            startActivityForResult(myIntent, REQUEST_CODE_SECOND_ACTIVITY)
            */

            // abrir intent de pagina web
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.javiercarrasco.es")
            )

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Log.d("DEBUG", "Hay un problema para encontrar un navegador.")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                var message = intent?.getStringExtra(RETURN_MESSAGE)
                Toast.makeText(this, "Result OK $message", Toast.LENGTH_SHORT).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Result Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}