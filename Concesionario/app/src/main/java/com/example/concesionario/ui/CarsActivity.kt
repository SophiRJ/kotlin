package com.example.concesionario.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.concesionario.R
import com.example.concesionario.adapter.AdaptadorMarcas
import com.example.concesionario.adapter.AdaptadorModelos
import com.example.concesionario.databinding.ActivityCarsBinding
import com.example.concesionario.model.Marca
import com.example.concesionario.model.Modelo
import com.google.android.material.snackbar.Snackbar

class CarsActivity : AppCompatActivity(),OnItemSelectedListener {
    private lateinit var binding: ActivityCarsBinding
    //lista
    private lateinit var listaMarcas:ArrayList<Marca>
    //adaptador
    private lateinit var adaptadorMarcas:AdaptadorMarcas
    //lista modelos
    private lateinit var listaModelos:ArrayList<Modelo>
    //adaptador
    private lateinit var adaptadorModelos:AdaptadorModelos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.spinnerMarcas.adapter
        instancias()
        acciones()
    }
    private fun acciones(){
        binding.spinnerMarcas.onItemSelectedListener=this
    }
    private fun instancias(){
        listaMarcas= arrayListOf(
            Marca("Mercedes",R.drawable.mercedes),
            Marca("BMV",R.drawable.bmw),
            Marca("Ford",R.drawable.ford),
            Marca("BYD",R.drawable.byd),
            Marca("Audi",R.drawable.audi),
            Marca("Peugeot",R.drawable.peugeot))
        adaptadorMarcas=AdaptadorMarcas(listaMarcas,applicationContext)
        binding.spinnerMarcas.adapter=adaptadorMarcas

        //inicializo la lista y despues el adaptador
        listaModelos= arrayListOf(
            Modelo(
                "C 63 S E PERFORMANCE",
                "Mercedes",
                140000,
                400,
                "Deportivo de mercedes",
                R.drawable.c63),
            Modelo(
                "E 63 S E PERFORMANCE",
                "Mercedes",
                240000,
                500,
                "Deportivo de mercedes",
                R.drawable.e63),
            Modelo(
                "RS 7 Sportback",
                "Audi",
                156000,
                600,
                "Deportivo de Audi",
                R.drawable.rs7),
            Modelo(
                "RS 8 Sportback",
                "Audi",
                156000,
                600,
                "Deportivo de Audi",
                R.drawable.rsq8),
            Modelo(
                "Mustang GT",
                "Ford",
                156000,
                600,
                "Deportivo de Ford",
                R.drawable.mustangt),
            Modelo(
                "Mustang Mach",
                "Ford",
                156000,
                600,
                "Deportivo de Ford",
                R.drawable.mustangmatch))
        adaptadorModelos= AdaptadorModelos(listaModelos,this)
        //Junto ambas cosas pero ademas aqui debo decirle como se dispondran los elementos dentro de la lista
        binding.recyclerModelos.adapter=adaptadorModelos
        //usamos linarLayout manager si queremos un elemento en cada fila
        //gridLayoutmanager para dos elementos en cada fila
        //orientacion=1->movil vertical
        if(resources.configuration.orientation==1){
            binding.recyclerModelos.layoutManager=
                LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        }else if(resources.configuration.orientation==2){//2-> movil girado
            binding.recyclerModelos.layoutManager=
               GridLayoutManager(this,2) //-> contexto y numero de filas que quiero que tenga
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val marcaSeleccionada:Marca =adaptadorMarcas.getItem(position)
        //vas a la lista y te quedas solo con los modelos que tienen como atributo marca
        //la misma que el spinner tiene seleccionada-> filter me quedo con todos los elementos que
        //coincidan-> me devuelve una lista sino coincide nadie me devulev una lista con size 0, no un nulo
        //parametro it por que solo tengo un elemento-> el modelo que estoy iterando
        val listaFiltrada:ArrayList<Modelo> =listaModelos.filter {
            it.marca.equals(marcaSeleccionada.nombre,true)
        }as ArrayList<Modelo>
        adaptadorModelos.actualizarLista(listaFiltrada)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}