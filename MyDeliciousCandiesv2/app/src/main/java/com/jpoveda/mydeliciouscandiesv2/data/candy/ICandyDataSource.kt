package com.jpoveda.mydeliciouscandiesv2.data.candy

import android.content.res.Resources
import java.io.File

interface ICandyDataSource {
    fun getList(): List<Candy>
    fun saveElement(candy: List<Candy>)
    //fun saveImgElement(candies: List<String>)
    //fun getImgList(): List<String>
}