package com.example.practicaimc.data.measurements

import android.content.Context
import com.example.practicaimc.utils.FileManager

class MeasurementFileDataSource(context: Context) : IMeasurementDataSource {
    val fileName = "historicoP3.txt"
    val context: Context = context

    override fun getList(): List<Measurement> {
        var listResult: List<String>? = ArrayList()
        try {
            listResult = FileManager.readFile(context, fileName)
        } finally {
            return listResult?.map { Measurement.fromString(it) } ?: ArrayList()
        }
    }
    override fun saveElement(measurements: List<Measurement>) {
        val listOfStrings: List<String> = measurements.map { it.toString() }
        FileManager.saveLinesInFile(context, fileName, listOfStrings)
    }
}