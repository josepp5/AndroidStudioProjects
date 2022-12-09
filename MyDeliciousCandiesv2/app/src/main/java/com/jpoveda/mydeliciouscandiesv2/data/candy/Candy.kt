package com.jpoveda.mydeliciouscandiesv2.data.candy

data class Candy(
    val name: String,
    val fabricante: String,
    val dulzor: Int,
    val tipo: String,
    val formato: String,
    val web: String,
    // val image: Bitmap
) {
    override fun toString(): String {
        return "${name};${fabricante};${dulzor};${tipo};${formato};${web}"
    }
    companion object {
        fun fromString(string: String): Candy {
            val splittedString = string.split(';')
            return Candy(
                splittedString[0],
                splittedString[1],
                Integer.parseInt(splittedString[2]),
                splittedString[3],
                splittedString[4],
                splittedString[5]
            )
        }
    }
}