package com.example.formulario

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formulario.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var correoRecuperado: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundleRecuperado: Bundle? =intent.extras?.getBundle("datos")//puede ser nulo por que puede
        //que no me pasen extras
        //recuperamos el correo
        correoRecuperado= bundleRecuperado?.getString("correo")?: "sin correo"

        binding.correo.text=correoRecuperado

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}