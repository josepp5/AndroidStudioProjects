package com.jpoveda.files.data.persons

import android.content.Context
import java.io.File

class PersonFileDataSource(context: Context, val filename : String) : IPersonDataSource {

    init {
        var file = File(filename)
        if (!file.exists()) {
            var l = ArrayList<Person>()
            l.add(Person("jose", "company", 1))
            l.add(Person("sdfdsfds", "fdsfds", 2))
            l.add(Person("fdsfds", "ghhgfjhgf", 3))
            saveList(context, l)
        }
    }
    override fun getList(context: Context): List<Person> {
        var list = ArrayList<Person>()
        context.openFileInput(filename).bufferedReader().useLines { lines ->
            for (s in lines){
                var splitLine = s.split(";")
                list.add(Person(splitLine[0], splitLine[1], splitLine[2].toInt()))
            }
        }
        return list
    }
    override fun saveList(context: Context, list: List<Person>) {
        var fileContents = StringBuffer()
        for (s in list){
            fileContents.appendLine(s.toString())
        }
        // Use this code to write in the .txt
        context.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toString().encodeToByteArray())
        }
    }
}