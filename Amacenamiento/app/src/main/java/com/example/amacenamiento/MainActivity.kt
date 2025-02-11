package com.example.amacenamiento

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.amacenamiento.DAO.UserDAO
import com.example.amacenamiento.databinding.ActivityMainBinding
import com.example.amacenamiento.model.User

class MainActivity : AppCompatActivity() ,OnClickListener{
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnBorrar.setOnClickListener(this)
        binding.btnInsertar.setOnClickListener(this)
        binding.btnActualizar.setOnClickListener(this)
        binding.btnSeleccionar.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btnBorrar.id->{
                val userDAO= UserDAO(applicationContext)
                userDAO.deletetUser("Sofia")
            }
            binding.btnInsertar.id->{
                val userDAO= UserDAO(applicationContext)
                userDAO.insertUser(User("Sofia",34))
            }
            binding.btnSeleccionar.id->{
                val userDAO=UserDAO(applicationContext)
                binding.textView.text=userDAO.getUsers().toString()
            }
            binding.btnActualizar.id->{
                val userDAO= UserDAO(applicationContext)
                userDAO.updetatUser("Sofia")
            }
        }
    }
}