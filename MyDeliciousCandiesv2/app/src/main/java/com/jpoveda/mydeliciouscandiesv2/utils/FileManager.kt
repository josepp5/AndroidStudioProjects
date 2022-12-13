package com.jpoveda.mydeliciouscandiesv2.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class FileManager {
    companion object {
        fun saveLinesInFile(context: Context, fileName: String, list: List<String>) {
            var fileContents = StringBuffer()
            for (s in list) {
                fileContents.appendLine(s)
            }
            // Use this code to write in the .txt
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(fileContents.toString().encodeToByteArray())
            }
        }

        fun saveImage(context: Context, fileName: String, image: Bitmap) {
            try {
                val fileOutPutStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fileOutPutStream)
                fileOutPutStream.flush()
                fileOutPutStream.close()

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun getImage(context: Context, fileName: String): Bitmap? {

            var bitmap: Bitmap? = null

            try {
                val inputStream = context.openFileInput(fileName)
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return bitmap
        }

        fun readFile(context: Context, fileName: String): List<String>? {
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