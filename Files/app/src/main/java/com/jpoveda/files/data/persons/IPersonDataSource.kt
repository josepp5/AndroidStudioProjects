package com.jpoveda.files.data.persons

import android.content.Context

interface IPersonDataSource {
    fun getList(context : Context): List<Person>
    fun saveList(context : Context, list: List<Person>)
}