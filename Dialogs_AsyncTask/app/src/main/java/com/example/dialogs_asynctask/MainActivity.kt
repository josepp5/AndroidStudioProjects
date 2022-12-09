package com.example.dialogs_asynctask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.scaleMatrix
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_login.*
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var myAsyncTask: MyAsyncTask

    // Para la corrutina
    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {

        // Simple Dialog
        btnAlert.setOnClickListener {
            MakeDialog()
        }
        // Dialog with Array
        btnAlertSingleList.setOnClickListener {
            MakeDialogSingleList()
        }
        // Dialog with "RadioButtons"
        btnAlertSingleChoice.setOnClickListener {
            MakeDialogSingleChoice()
        }
        // Dialog with "checkbox list"
        btnAlertMultiChoice.setOnClickListener {
            MakeDialogMultiChoice()
        }
        // Custom dialog (login)
        btnCustomDialog.setOnClickListener {
            MakeCustomDialog()
        }
        // Date Picker Dialog
        btnDatePicker.setOnClickListener {
            MakeDatePicker()
        }
        // Time picker Dialog
        btnTimePicker.setOnClickListener {
            MakeTimePicker()
        }

        // ProgressBar 
        btnProgressBar.setOnClickListener {
            //  makeProgress()
            myAsyncTask = MyAsyncTask(
                this,
                progressBar,
                btnProgressBar,
                btnProgressBarCancel
            )
            // Params will be send to the doInBackground(params) for whatever in needs
            myAsyncTask.execute(100, 100)
        }
        // ProgressBar Cancel button
        btnProgressBarCancel.setOnClickListener {
            // In case there is an AsyncTask it will be cancelled
            myAsyncTask?.cancel(true)
        }
        // Corrutina con progressbar
        btnProgressBarCorrutina.setOnClickListener {
            job = makeTask(
                10,
                btnProgressBarCorrutina,
                btnProgressBarCancelCorrutina,
                progressBarCorrutina
            )
        }
        btnProgressBarCancelCorrutina.setOnClickListener {
            job.let {
                job?.cancel().apply {
                    Toast.makeText(
                        this@MainActivity,
                        "corrutina cancelada!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            btnProgressBarCorrutina.isEnabled = true
            btnProgressBarCancelCorrutina.isEnabled = false
            progressBarCorrutina.progress = 0
        }


        // Boton para notificaciones
        btnNotification.setOnClickListener {
            makeNotification()
        }
    }

    private fun makeNotification(){
        val builder = NotificationCompat.Builder(this, "com.example.dialogs_asynctask.MainActivity")
        // Valores de la notificacion
        builder.apply {
            setSmallIcon(R.mipmap.ic_launcher_round)
            setContentTitle("Mi primera notificación")
            setContentText("Esta será la primera notificación creada.")
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

    /*
     * Función de suspensión que detiene la ejecución de la corrutina
     * hasta que finaliza.
     */
    suspend fun task(duracion: Long): Boolean {
        Log.d("SUSPEND FUN", "Simulando una tarea!")
        delay(duracion)
        return true
    }

    private fun makeTask(
        duracion: Int, btnStart: Button,
        btnCancel: Button, progressBar: ProgressBar
    ) = GlobalScope.launch(Dispatchers.Main) {
        // Preparación de la corrutina.
        btnStart.isEnabled = false
        btnCancel.isEnabled = true
        progressBar.progress = 0
        withContext(Dispatchers.IO) { // Tarea principal.
            var contador = 0
            while (contador < duracion) {
                if (task((duracion * 50).toLong())) {
                    contador++
                    progressBar.progress = (contador * 100) / duracion
                }
            }
        }
        // Finaliza la corrutina.
        btnStart.isEnabled = true
        btnCancel.isEnabled = false
        progressBar.progress = 0
    }

    private fun makeProgress() {
        // this var contains the progress status
        var progressBarStatus = 0
        progressBar.progress = 0
        btnProgressBar.isEnabled = false

        // Part of code to compile in secondary thread
        var runnable = Runnable {
            while (progressBarStatus < 100) {
                progressBarStatus += 10
                progressBar.progress = progressBarStatus
                Thread.sleep(1000)
            }
            this@MainActivity.runOnUiThread {
                Toast.makeText(this, "Tarea finalizada!!", Toast.LENGTH_SHORT).show()
                btnProgressBar.isEnabled = true
            }
        }
        Thread(runnable).start()
    }

    fun MakeTimePicker() {
        val cal = Calendar.getInstance()
        val timePickerListener = TimePickerDialog.OnTimeSetListener() { _, hour, minute ->

        }
        // This code initializes the dialog with the time setted
        TimePickerDialog(
            this,
            timePickerListener,
            cal.get(Calendar.HOUR),
            cal.get(Calendar.MINUTE),
            true,
        ).show()
    }

    fun MakeDatePicker() {
        val cal = Calendar.getInstance()
        val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            cal.set(year, month, day)
            Toast.makeText(
                this@MainActivity,
                "El dia seleccionado es $day $month $year",
                Toast.LENGTH_LONG
            ).show()
        }
        // This code initializes the dialog with the date setted
        DatePickerDialog(
            this,
            datePickerListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH),
        ).show()
    }

    fun MakeCustomDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setView(layoutInflater.inflate(R.layout.dialog_login, null))
            setPositiveButton(getString(R.string.dialog_button_positive)) { dialog, _ ->
                val userName = (dialog as AlertDialog).username.text
                val password = (dialog).password.text
                Toast.makeText(
                    this@MainActivity,
                    "El usuario es $userName $password",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        alertDialog.show()
    }

    fun MakeDialogMultiChoice() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle(getString(R.string.dialog_title))
            /// Needs to add null on checkedItems and isChecked
            setMultiChoiceItems(R.array.city, null) { _, position, isChecked ->
                val city = resources.getStringArray(R.array.city)[position]
                val message = getString(R.string.toast_message)
                val messageFormatted = String.format(message, city)
                Toast.makeText(this@MainActivity, messageFormatted, Toast.LENGTH_LONG).show()
            }
            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    "has dado en el boton positivo",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        alertDialog.show()
    }

    fun MakeDialogSingleChoice() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle(getString(R.string.dialog_title))
            /// Needs to add checkedItem index
            setSingleChoiceItems(R.array.city, -1) { _, position ->
                val city = resources.getStringArray(R.array.city)[position]
                val message = getString(R.string.toast_message)
                val messageFormatted = String.format(message, city)
                Toast.makeText(this@MainActivity, messageFormatted, Toast.LENGTH_LONG).show()
            }
            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    "has dado en el boton positivo",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        alertDialog.show()
    }

    fun MakeDialogSingleList() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle(getString(R.string.dialog_title))
            setItems(R.array.city) { _, position ->
                val city = resources.getStringArray(R.array.city)[position]
                val message = getString(R.string.toast_message)
                val messageFormatted = String.format(message, city)
                Toast.makeText(this@MainActivity, messageFormatted, Toast.LENGTH_LONG).show()
            }
        }
        alertDialog.show()
    }

    fun MakeDialog() {
        val alertDialog = AlertDialog.Builder(this)
        // Change dialog properties this way
        alertDialog.setTitle(getString(R.string.dialog_title))
        alertDialog.setNegativeButton(getString(R.string.dialog_button_negative)) { _, _ ->
            Toast.makeText(this, "has dado en el boton negativo", Toast.LENGTH_LONG).show()
        }/// OR
        // Other way to change is
        alertDialog.apply {
            setMessage(getString(R.string.dialog_message))

            setPositiveButton(getString(R.string.dialog_button_positive)) { _, _ ->
                Toast.makeText(
                    this@MainActivity,
                    "has dado en el boton positivo",
                    Toast.LENGTH_LONG
                ).show()
            }
            setNeutralButton(getString(R.string.dialog_button_neutral)) { _, _ ->
                Toast.makeText(this@MainActivity, "has dado en el boton neutro", Toast.LENGTH_LONG)
                    .show()
            }
        }
        alertDialog.show()
    }
}