package com.jpoveda.mydeliciouscandiesv2.data.candy

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toDrawable
import com.jpoveda.mydeliciouscandiesv2.utils.FileManager
import java.io.File

class CandyFileDataSource(context: Context) : ICandyDataSource {
    val imagesName: String = "candy"
    val fileName = "candyList2.txt"
    val context: Context = context

    override fun getList(): List<Candy> {
        var listResult: List<String>? = ArrayList()
        try {
            listResult = FileManager.readFile(context, fileName)
        } finally {
            return listResult?.map {
                val candy = Candy.fromString(it)
                val image = FileManager.getImage(context, imagesName + candy.id)
                candy.image = image
                candy
            } ?: ArrayList()
        }
    }

    override fun saveElements(candies: List<Candy>) {
        val listOfStrings: List<String> = candies.map { it.toString() }
        FileManager.saveLinesInFile(context, fileName, listOfStrings)
        candies.forEach { candy ->
            candy.image?.let { FileManager.saveImage(context, imagesName + candy.id,it) }
        }
    }
}