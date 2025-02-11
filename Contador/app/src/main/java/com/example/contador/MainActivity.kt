package com.example.contador

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var contador=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val textViewContador= binding.textViewContador
        //inicializar el textView con el valor del contador
        textViewContador.text=contador.toString()
        //configurar el contador para incrementar el contador
        binding.btAdd.setOnClickListener {
            contador++
            textViewContador.text=contador.toString()
        }
        binding.btNewActivity.setOnClickListener { val intent= Intent(baseContext, MostrarContador::class.java)
            intent.putExtra("contador",contador)
            startActivity(intent)}
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}