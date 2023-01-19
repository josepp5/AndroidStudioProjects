package com.jpoveda.dbadapter.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "persona.db"
        val DATABASE_VERSION = 1
        val TABLA_AMIGOS = "amigos"
        val COLUMNA_ID = "_id"
        val COLUMNA_NOMBRE = "nombre"
        val COLUMNA_APELLIDOS = "apellidos"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val createTableAmigos="CREATE TABLE $TABLA_AMIGOS " +
                    "($COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMNA_NOMBRE TEXT, " +
                    "$COLUMNA_APELLIDOS TEXT)"
            db!!.execSQL(createTableAmigos)
        }catch (e : SQLiteException){

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    fun addAmigo(name: String, lastName: String){
        val data = ContentValues()
        data.put(COLUMNA_NOMBRE,name)
        data.put(COLUMNA_APELLIDOS, lastName)

        val db = this.writableDatabase
        db.insert(TABLA_AMIGOS,null,data)
        db.close()
    }

    fun delAmigo(id: Int) : Int{
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        // Delete necesita la tabla, la condicion, argumentos
        val result = db.delete(TABLA_AMIGOS,"$COLUMNA_ID=?",args)
        // Or giving 2 args
        //val result = db.delete(TABLA_AMIGOS,"nombre LIKE '?%' and apellido LIKE '?%'",args)

        db.close()
        return result
    }
}