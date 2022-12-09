package com.example.practicaimc.utils

import android.util.Log
import java.util.Calendar

class CalendarHelper {
    companion object {
        fun getFormattedDate(): String {
            // ENERO - 0, FEBRERO - 1, ..., DICIEMBRE - 11
            val hoy = Calendar.getInstance()
            Log.d(
                "FECHA",
                "${hoy.get(Calendar.DAY_OF_MONTH)}" +
                        "/${hoy.get(Calendar.MONTH) + 1}" +
                        "/${hoy.get(Calendar.YEAR)}")
            return "${hoy.get(Calendar.DAY_OF_MONTH)}" + "/${hoy.get(Calendar.MONTH) + 1}" + "/${hoy.get(Calendar.YEAR)}"
        }
    }
}