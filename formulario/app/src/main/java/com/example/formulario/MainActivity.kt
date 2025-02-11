package com.example.formulario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formulario.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        //nos pide un objeto de tipo oncliclistener ->this
        binding.botonLogin.setOnClickListener(this)
        binding.botonLimpiar.setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(p0: View?) {
        //quien lo ha pulsado
            //login
            //limpiar
        when(p0?.id){
            binding.botonLogin.id ->{
                if (binding.editContraseA.text.isNotEmpty()&&binding.editCorrreo.text.isNotEmpty()){
                    // Cambiar de pantalla -> origen - destino
                    // applicationCtx o this
                    val intent: Intent= Intent(applicationContext, SecondActivity ::class.java)
                    val bundle: Bundle=Bundle()
                    bundle.putString("correo",binding.editCorrreo.text.toString())//metemos en el bundle el correo
                    intent.putExtra("datos",bundle)//metemos en el intent el bundle
                    startActivity(intent)// esto activa la accion
                } else{
                    Snackbar.make(binding.root,"Faltan datos", Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.botonLimpiar.id->{
                limpiar()
            }
        }
    }
    fun limpiar(){
        binding.editContraseA.text.clear()
        binding.editCorrreo.text.clear()
    }

    override fun onRestart() {
        super.onRestart()
        limpiar()
    }
}
//primera pantalla -> onCreate - on Start- onResume -VIENDO- onPause - onStop-> SegundoPlano ->onRestart
//segunda pantalla -> onCreate - on Start - onResume -VIENDO- cuando le doy atras -> onPAuse - onStop -onDestroy