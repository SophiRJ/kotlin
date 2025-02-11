package com.example.retodosrev

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.example.retodosrev.dao.ElementDao
import com.example.retodosrev.databinding.ActivityMainBinding
import com.example.retodosrev.model.Elemento

class MainActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgregar.setOnClickListener(this)
        binding.btnBorrar.setOnClickListener(this)
        binding.actualizarLista.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btnAgregar.id->{
                if(binding.editText.text.isNotEmpty()){
                    val elementDao=ElementDao(applicationContext)
                    elementDao.insertElement(Elemento(binding.editText.text.toString()))
                    binding.editText.text.clear()
                    Toast.makeText(this,"Elemento insertado",Toast.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root,"Escriba elemento a insertar",Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.btnBorrar.id->{
                if(binding.editText.text.isNotEmpty()){
                    val elementDao=ElementDao(applicationContext)
                    elementDao.deleteElement(binding.editText.text.toString())
                    binding.editText.text.clear()
                    Toast.makeText(this,"Elemento borrado",Toast.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root,"Escriba elemento a borrar",Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.actualizarLista.id->{
                val elementDao=ElementDao(applicationContext)
                val elementos=elementDao.getElements()
                if(elementDao.getElements().size==0){
                    Snackbar.make(binding.root,"No hay elementos insertados",Snackbar.LENGTH_SHORT).show()
                    binding.vistaTexto.text=""
                }else{
                    val elementosTexto = elementos.joinToString(separator = "\n") { it.name }
                    binding.vistaTexto.text = elementosTexto
                }
            }
        }
    }
}