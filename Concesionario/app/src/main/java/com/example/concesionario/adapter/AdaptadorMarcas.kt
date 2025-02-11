package com.example.concesionario.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.concesionario.R
import com.example.concesionario.model.Marca

class AdaptadorMarcas(var listaMarca: ArrayList<Marca>,var contexto:Context) :BaseAdapter(){
    override fun getCount(): Int {
        //cuantas filas se pintaran o redenrizar
        return listaMarca.size
    }

    override fun getItem(position: Int): Marca {
     //el item de cada fila
        return listaMarca[position]
    }

    override fun getItemId(position: Int): Long {
        //retorna el id de cada fila
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //el aspecto de cada fila-> necesito el xml
        //contexto de donde vienen las cosas
        //XML
        val view:View=LayoutInflater.from(contexto).inflate(R.layout.item_marca,parent,false)
        //buscamos y sacamos la imagen y el texto del xml mediante sus id
        //aqui ya tenemos los elementos donde queremos dejar las cosas
        val imagen:ImageView= view.findViewById(R.id.imagenLogoSp)
        val texto:TextView=view.findViewById(R.id.textoMarcaSp)
        //el metodo getView se ejecuta tantas veces como filas haya
        //itera sobre cada elemento y va metiendo en la imagen y texto diferentes cosas
        //necesito el arraylist marca para capturar sus propiedades y meterlas en imagen y texto
        val marca:Marca=listaMarca[position]
        imagen.setImageResource(marca.logo)
        texto.text=marca.nombre
        return view
    }
}