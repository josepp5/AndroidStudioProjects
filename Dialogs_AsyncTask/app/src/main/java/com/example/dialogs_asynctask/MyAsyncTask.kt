package com.example.dialogs_asynctask

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar

class MyAsyncTask(
    val contexto: Context,
    val progressBar: ProgressBar,
    val botonStart: Button,
    val botonCancelar: Button
    // Type of data that will be send as parameter to the methods
) : AsyncTask<Int, Int, Int>() {

    // Code to execute in main Thread before (usually the views)
    override fun onPreExecute() {
        super.onPreExecute()
        progressBar.progress = 0
        botonStart.isEnabled = false
        botonCancelar.isEnabled = true
    }

    // Code to execute in Secondary Thread
    override fun doInBackground(vararg params: Int?): Int {
        if (params.size == 2) {
            var contador = 0
            while (contador < params[0]!!) {
                try {
                    contador++
                    Thread.sleep(params[1]!!.toLong())
                } catch (e: Exception) {
                    Log.println(
                        Log.WARN,
                        "doInBackground",
                        e.message.toString()
                    )
                }
                // Comprobamos si la tarea ha sido cancelada.
                if (!isCancelled)
                    // Calls onProgressUpdate and sends the params
                    publishProgress(
                        (((contador + 1) * 100 / params[0]!!).toFloat()).toInt()
                    )
                else break
            }
            return 1
        } else return -1
    }

    // Code to execute when publishProgress is called
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        progressBar.progress = values[0]!!
    }

    // Code to execute in main Trhead after the in secondary Thread is done
    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
    }

    // Code to execute in main Trhead  when the AsyncTask is cancelled
    override fun onCancelled(result: Int?) {
        super.onCancelled(result)
        botonStart.isEnabled = true
        botonCancelar.isEnabled = false
        progressBar.progress = 0
    }
}