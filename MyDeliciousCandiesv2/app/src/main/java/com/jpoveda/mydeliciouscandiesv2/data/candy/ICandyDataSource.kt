package com.jpoveda.mydeliciouscandiesv2.data.candy

interface ICandyDataSource {
    fun getList(): List<Candy>
    fun saveElement(candy: List<Candy>)
}