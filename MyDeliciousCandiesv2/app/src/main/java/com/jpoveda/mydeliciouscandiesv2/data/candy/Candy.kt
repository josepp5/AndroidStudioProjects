package com.jpoveda.mydeliciouscandiesv2.data.candy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.util.UUID

data class Candy(
    val id: UUID,
    val name: String,
    val fabricante: String,
    val dulzor: Int,
    val tipo: String,
    val formato: String,
    val web: String,
    var image: Bitmap?,
    var favorito: Boolean = false
) {
    override fun toString(): String {
        return "${id};${name};${fabricante};${dulzor};${tipo};${formato};${web};${favorito}"
    }

    companion object {
        fun fromString(string: String): Candy {
            val splittedString = string.split(';')
            return Candy(
                UUID.fromString(splittedString[0]),
                splittedString[1],
                splittedString[2],
                Integer.parseInt(splittedString[3]),
                splittedString[4],
                splittedString[5],
                splittedString[6],
                null,
                splittedString[7] == "true",
            )
        }
    }
}