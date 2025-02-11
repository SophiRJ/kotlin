package com.example.registro

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registro.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var bundleRecuperado:Bundle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        bundleRecuperado=intent.extras?.getBundle("datos")
        binding.textUser.text=bundleRecuperado?.getString("correo")?:"sin datos"

    }
}