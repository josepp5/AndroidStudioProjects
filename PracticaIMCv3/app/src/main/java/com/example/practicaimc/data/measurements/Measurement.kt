package com.example.practicaimc.data.measurements

data class Measurement(var date: String, val sexo: String, val result: Double, val rango: String, var peso: Double, var altura: Double) {
    override fun toString(): String {
        return "${date};${sexo};${result};${rango};${peso};${altura}"
    }

    companion object {
        fun fromString(string: String): Measurement {
            val splittedString = string.split(';')
            return Measurement(splittedString[0],
                splittedString[1],
                splittedString[2].toDouble(),
                splittedString[3],
                splittedString[4].toDouble(),
                splittedString[5].toDouble()
            )
        }
    }
}