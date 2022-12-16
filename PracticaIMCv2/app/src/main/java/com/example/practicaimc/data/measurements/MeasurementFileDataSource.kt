package com.example.practicaimc.data.measurements

import android.content.Context
import com.example.practicaimc.R
import com.example.practicaimc.utils.FileManager

class MeasurementFileDataSource(context: Context) : IMeasurementDataSource {
    val fileName = "historico.txt"
    val context: Context = context

    override fun getList(): List<Measurement> {
        val list = FileManager.readFile(context, fileName)
        return list?.map { Measurement.fromString(it) } ?: ArrayList()
    }
    override fun saveElement(measurements: List<Measurement>) {
        val listOfStrings: List<String> = measurements.map { it.toString() }
        FileManager.saveLinesInFile(context, fileName, listOfStrings)
    }
}