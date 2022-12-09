package com.example.practicaimc.data.measurements

data class Measurement(var date: String, val sexo: String, val result: Double, val rango: String) {
    override fun toString(): String {
        return "${date};${sexo};${result};${rango}"
    }

    companion object {
        fun fromString(string: String): Measurement {
            val splittedString = string.split(';')
            return Measurement(splittedString[0], splittedString[1], splittedString[2].toDouble(), splittedString[3])
        }
    }
}