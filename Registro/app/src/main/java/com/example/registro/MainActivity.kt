package com.example.registro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.registro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClickListener,OnCheckedChangeListener{
    private lateinit var binding: ActivityMainBinding
    private var correo:String=""
    private var pass:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater);
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(this)
        binding.tvRegistrer.setOnClickListener(this)
        binding.btnVaciar?.setOnClickListener(this)
        binding.bntToog.setOnCheckedChangeListener(this)

        correo=savedInstanceState?.getString("correo")?:""
        pass=savedInstanceState?.getString("pass")?:""
    }

    override fun onClick(p0: View?) {
        lateinit var intent:Intent
        when(p0?.id){
            binding.btnLogin.id->{
                intent=Intent(applicationContext,HomeActivity::class.java);
                val bundle:Bundle=Bundle();
                bundle.putString("correo",binding.etEmail.text.toString())
                bundle.putString("pass",binding.etPassword.text.toString())
                intent.putExtra("datos",bundle)
                startActivity(intent)
            }
            binding.tvRegistrer.id->{
                intent=Intent(applicationContext,RegisterActivity::class.java)
                startActivity(intent)
            }
            binding.btnVaciar?.id->{
                binding.etEmail.text.clear()
                binding.etPassword.text.clear()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("correo",binding.etEmail.text.toString())
        outState.putString("pass",binding.etPassword.text.toString())
    }

    override fun onRestart() {
        super.onRestart()
        binding.etEmail.text.clear()
        binding.etPassword.text.clear()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        binding.btnLogin.isEnabled=isChecked
    }
}