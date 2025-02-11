package com.example.concesionario.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.concesionario.R
import com.example.concesionario.model.Modelo
import com.example.concesionario.ui.DetailActivity

class AdaptadorModelos(var lista:ArrayList<Modelo>, var contexto:Context):RecyclerView.Adapter<AdaptadorModelos.MyHolder>() {
    class MyHolder(itemView: View) : ViewHolder(itemView){
        //saco cada uno de los elementos que hay dentro del XML(patron de la fila, boton texto, imagen)
        val boton= itemView.findViewById<Button>(R.id.btnModelo)
        val texto= itemView.findViewById<TextView>(R.id.textoModelo)
        val imagen=itemView.findViewById<ImageView>(R.id.imagenModelo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        //Crea el patron de la filas-> Holder. aqui traemos el xml
        val vista=LayoutInflater.from(contexto).inflate(R.layout.item_modelo,parent,false)
        val holder:MyHolder=MyHolder(vista)//lo iguamos a la clase y esta me pide parametro la vista-> xml
        return holder //retornamos el patron con esto ya tengo acceso a todos los elementos del xml
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        //como se comporta cada fila
        //en cada fila pondre el modelo que le toque de la posicion
        //este metodo se ejecuta tantas veces como elementos haya
        val modelo=lista[position]
        holder.imagen.setImageResource(modelo.imagen)
        holder.texto.text=modelo.modelo
        holder.boton.setOnClickListener{

            val intent:Intent=Intent(contexto,DetailActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val bundle:Bundle=Bundle()
            bundle.putSerializable("coche",modelo)
            intent.putExtra("datos",bundle)
            contexto.startActivity(intent)
        }
        holder.imagen.setOnLongClickListener {
            lista.remove(modelo)
            notifyItemRemoved(position)
            true
        }
    }
    fun actualizarLista(listaModelo: ArrayList<Modelo>){
        lista=listaModelo
        notifyDataSetChanged()
    }
}