package com.example.retodosrev.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.retodosrev.database.DBHelper
import com.example.retodosrev.model.Elemento

class ElementDao(var context: Context) {
    fun insertElement(elemento: Elemento){
        val database: SQLiteDatabase = DBHelper(context,"element_db",null,1).writableDatabase

        val content:ContentValues=ContentValues()
        content.put("name",elemento.name)
        database.insert("element",null,content)
    }
    fun deleteElement(name:String){
        val database:SQLiteDatabase= DBHelper(context,"element_db",null,1).writableDatabase
        database.delete("element","name=?", arrayOf(name))
    }
    fun getElements():ArrayList<Elemento> {
        val elementos = ArrayList<Elemento>()
        val database: SQLiteDatabase = DBHelper(context, "element_db", null, 1).readableDatabase
        val cursor: Cursor =
            database.query("element", arrayOf("name"), null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            elementos.add(Elemento(name))
            //mostramos los datos por el logCat
            Log.v("datbase_users", "Name: $name ")
        }
        cursor.close()
        return elementos
    }
}