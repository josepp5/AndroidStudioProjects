package com.example.practicaimc.utils

import android.content.Context
import com.example.practicaimc.R

class IMCCalculator {
    companion object {
        fun Calculate(height: Double, weight: Double): Double {
            val heightInMeters = height/100
            return weight / (heightInMeters * heightInMeters)
        }

        fun Rango(context: Context, imc: Double, isMale: Boolean): String {
            if (isMale){
                if (imc < 18.5) return context.getString(R.string.peso_inferior)
                else if (imc < 24.9) return context.getString(R.string.peso_normal)
                else if (imc < 29.9) return context.getString(R.string.sobrepeso)
                else return context.getString(R.string.obesidad)
            } else {
                if (imc < 18.5) return context.getString(R.string.peso_inferior)
                else if (imc < 23.9) return context.getString(R.string.peso_normal)
                else if (imc < 28.9) return context.getString(R.string.sobrepeso)
                else return context.getString(R.string.obesidad)
            }
        }
    }
}