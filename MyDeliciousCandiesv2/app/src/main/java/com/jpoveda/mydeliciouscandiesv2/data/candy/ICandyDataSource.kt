package com.jpoveda.mydeliciouscandiesv2.data.candy

import android.content.res.Resources
import java.io.File

interface ICandyDataSource {
    fun getList(): List<Candy>
    fun saveElements(candy: List<Candy>)
}