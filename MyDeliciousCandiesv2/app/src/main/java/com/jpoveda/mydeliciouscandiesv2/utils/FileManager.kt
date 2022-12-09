package com.example.practicaimc.utils

import android.content.Context
import java.io.File

class FileManager {
    companion object {
        fun saveLinesInFile(context: Context, fileName: String, list: List<String>) {
            var fileContents = StringBuffer()
            for (s in list){
                fileContents.appendLine(s)
            }
            // Use this code to write in the .txt
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(fileContents.toString().encodeToByteArray())
            }
        }

        fun readFile(context: Context, fileName: String) : List<String>? {
            var list: MutableList<String> = ArrayList()
            context.openFileInput(fileName).bufferedReader().useLines { lines ->
                lines.forEach {
                    list.add(it)
                }
            }
            return list
        }
    }
}