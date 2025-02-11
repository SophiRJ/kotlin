package com.example.estados

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener, OnCheckedChangeListener{
    private var contador=0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contador=savedInstanceState?.getInt("contador") ?:0
        binding.textNumero.text=contador.toString()

        binding.btnAnadir.setOnClickListener(this)
        binding.btnReset?.setOnClickListener(this)
        binding.checkEdicion.setOnCheckedChangeListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btnAnadir.id->{
                if (binding.editEdicion.text.isNotEmpty()){
                    contador++
                    binding.textNumero.setText(contador.toString())
                    binding.editEdicion.text.clear()
                }
            }
            binding.btnReset?.id->{
                contador=0
                binding.textNumero.text=contador.toString()
            }
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
        when(p0?.id){
            binding.checkEdicion.id->{
                binding.editEdicion.isEnabled=isChecked
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //se ejecuta para guardar el estado justo antes de que se rompa la app
        //la app se rompe cuando hacemos cualquier configuracion del telefono
        outState.putInt("contador",contador)
    }
}