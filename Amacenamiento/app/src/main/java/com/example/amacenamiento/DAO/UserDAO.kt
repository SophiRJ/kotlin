package com.example.amacenamiento.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.amacenamiento.databse.DBHelper
import com.example.amacenamiento.model.User

class UserDAO(var context: Context) {

    fun insertUser(user: User){

        //accedemos ala base de datos en modo escritura
        val database:SQLiteDatabase= DBHelper(context,"user_db",null,1).writableDatabase
        //.insert querys parametrizadas por seguridad nos pide el contentValue ->
        // objeto que tiene el par clave-valor de la columna y el valor que queremos insertar-> creamos
        val content:ContentValues= ContentValues()
        content.put("name",user.name)
        content.put("age",user.age)
        database.insert("user",null,content)
    }
    fun deletetUser(name:String){
        //accedemos ala base de datos en modo escritura
        val database:SQLiteDatabase= DBHelper(context,"user_db",null,1).writableDatabase
        //.delete from adasjdbashdb WHERE AKSDNASDN=?
        // querys parametrizadas por seguridad
        database.delete("user","name=?", arrayOf(name))
        // esto me devuelve un int el numero de filas que han sido borradas
    }
    fun updetatUser(name:String){
        //accedemos ala base de datos en modo escritura
        val database:SQLiteDatabase= DBHelper(context,"user_db",null,1).writableDatabase
        val  content= ContentValues()
        content.put("name","NombreNuevo")
        //UPDATE users SET nombre = NombreNuevo WHERE name=Borja
        database.update("user",content,"name=?", arrayOf(name))
        // esto me devuelve un int el numero de filas que han sido actualizadas
    }
    fun getUsers():Int{
        var contador=0
        //saco la bd en modo lectura para hacer un select-> conjunto de valores
        val database:SQLiteDatabase= DBHelper(context,"user_db",null,1).readableDatabase
        val cursor:Cursor = database.query("user", arrayOf("name","age"),null,null,null,null,null,null)
        while(cursor.moveToNext()){
            contador++
            val name= cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val age= cursor.getInt(cursor.getColumnIndexOrThrow("age"))
            //mostramos los datos por el logCat
            Log.v("datbase_users","Name: $name Age: $age")
        }
        return contador
    }
}