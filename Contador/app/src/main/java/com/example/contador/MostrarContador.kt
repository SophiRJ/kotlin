package com.example.contador

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contador.databinding.ActivityMainBinding
import com.example.contador.databinding.ActivityMostrarContadorBinding

class MostrarContador : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarContadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMostrarContadorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contador:Int =intent.getIntExtra("contador",0)
        binding.muestraContador.text=contador.toString()
        binding.btClose.setOnClickListener {
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}