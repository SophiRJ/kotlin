package com.example.registro

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registro.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var listaNacionalidades:ArrayList<CharSequence>
    private lateinit var adapter:ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.btnRegiistrar.setOnClickListener(this)
        instancias()
    }

    private fun instancias() {
        listaNacionalidades= arrayListOf("Español","Aleman","Ingles","Italiano")
        adapter=ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,listaNacionalidades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinerNacionalidad.adapter=adapter

        listaNacionalidades.add("Finlades")
        adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.btnRegiistrar.id->{
                val perfil:String=binding.spinerPerfil.selectedItem.toString()
                Log.v("perfil",perfil.toString())
            }
        }
    }
}