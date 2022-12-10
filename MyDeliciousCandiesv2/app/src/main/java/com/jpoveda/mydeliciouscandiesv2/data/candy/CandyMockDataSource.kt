package com.jpoveda.mydeliciouscandiesv2.data.candy

class CandyMockDataSource() : ICandyDataSource {
    override fun getList(): List<Candy> {
        val items = ArrayList<Candy>()
        items.add(Candy("lenguas", "fini", 2, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("caramelos", "kojac", 4, "Gominola", "Individual", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("chupa-chups", "kojac", 1, "Masticable", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("corazones", "fini", 5, "Masticable", "Individual", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("pirufrutas", "kojac", 4, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("rosquillas", "fini", 2, "Gominola", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("frutimelos", "kojac", 1, "Masticable", "Individual", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("ositos", "kojac", 5, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("habichuelas", "fini", 4, "Gominola", "Individual", "https://www.fiesta.es/producto/kojak"))
        items.add(Candy("lacasitos", "kojac", 2, "Masticable", "Bolsa", "https://www.fiesta.es/producto/kojak"))
        return items
    }
    override fun saveElement(measurement: List<Candy>) {
        return
    }
}