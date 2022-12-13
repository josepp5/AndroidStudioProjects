package com.jpoveda.mydeliciouscandiesv2.data.candy

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.jpoveda.mydeliciouscandiesv2.R
import java.util.*
import kotlin.collections.ArrayList

abstract class CandyMockDataSource() : ICandyDataSource {
    override fun getList(): List<Candy> {

        val items = ArrayList<Candy>()

        items.add(Candy(UUID.randomUUID(),"lenguas", "fini", 2, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"caramelos", "kojac", 4, "Gominola", "Individual", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"chupa-chups", "kojac", 1, "Masticable", "Bolsa", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"corazones", "fini", 5, "Masticable", "Individual", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"pirufrutas", "kojac", 4, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"rosquillas", "fini", 2, "Gominola", "Bolsa", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"frutimelos", "kojac", 1, "Masticable", "Individual", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"ositos", "kojac", 5, "Chicle", "Bolsa", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"habichuelas", "fini", 4, "Gominola", "Individual", "https://www.fiesta.es/producto/kojak",null))
        items.add(Candy(UUID.randomUUID(),"lacasitos", "kojac", 2, "Masticable", "Bolsa", "https://www.fiesta.es/producto/kojak",null))
        return items
    }
    override fun saveElement(measurement: List<Candy>) {
        return
    }
}