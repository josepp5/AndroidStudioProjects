package com.example.practicaimc.data.measurements

interface IMeasurementDataSource {
    fun getList(): List<Measurement>
    fun saveElement(measurement: List<Measurement>)
}