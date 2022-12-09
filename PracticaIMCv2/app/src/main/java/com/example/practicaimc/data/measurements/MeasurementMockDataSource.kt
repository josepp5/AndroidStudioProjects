package com.example.practicaimc.data.measurements

class MeasurementMockDataSource () : IMeasurementDataSource {
    override fun getList(): List<Measurement> {
        val items = ArrayList<Measurement>()
        items.add(Measurement("14/8/1997","Hombre", 23.2, "Gordo"))
        items.add(Measurement("15/9/1997","Hombre", 33.0, "Gordo"))
        items.add(Measurement("16/10/1997","Mujer", 24.5, "Gordo"))
        items.add(Measurement("17/11/1997","Hombre", 24.5,"Gordo"))
        items.add(Measurement("18/12/1997","Mujer", 24.5, "Gordo"))
        return items
    }
    override fun saveElement(measurement: List<Measurement>) {
        return
    }
}