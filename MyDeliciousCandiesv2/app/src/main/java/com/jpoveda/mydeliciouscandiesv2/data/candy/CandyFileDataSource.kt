package com.jpoveda.mydeliciouscandiesv2.data.candy

import android.content.Context
import com.example.practicaimc.utils.FileManager

class CandyFileDataSource(context: Context) : ICandyDataSource {
    val fileName = "candyList.txt"
    val context: Context = context

    override fun getList(): List<Candy> {
        var listResult: List<String>? = ArrayList()
        try {
            listResult = FileManager.readFile(context, fileName)
        } finally {
            return listResult?.map { Candy.fromString(it) } ?: ArrayList()
        }
    }
    override fun saveElement(candies: List<Candy>) {
        val listOfStrings: List<String> = candies.map { it.toString() }
        FileManager.saveLinesInFile(context, fileName, listOfStrings)
    }
}