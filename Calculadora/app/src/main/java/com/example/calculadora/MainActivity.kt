package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity

import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClickListener{
    private lateinit var binding: ActivityMainBinding
    // Variables para manejar las operaciones
    private var numeroActual=""
    private var operador = ""
    private var primerNumber = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asignar el listener a todos los botones
        val buttons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9,
            binding.btnSuma, binding.btnResta, binding.btnMultiplicacion, binding.btnDivision,
            binding.btnIgual, binding.btnClear
        )

        for (button in buttons) {
            button.setOnClickListener(this) // Asignamos el mismo listener
        }

    }
    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btn1.id->agregarNumero("1")
            binding.btn2.id->agregarNumero("2")
            binding.btn3.id->agregarNumero("3")
            binding.btn4.id->agregarNumero("4")
            binding.btn5.id->agregarNumero("5")
            binding.btn6.id->agregarNumero("6")
            binding.btn7.id->agregarNumero("7")
            binding.btn8.id->agregarNumero("8")
            binding.btn9.id->agregarNumero("9")
            binding.btn0.id->agregarNumero("0")

            binding.btnSuma.id->operador("+")
            binding.btnResta.id->operador("-")
            binding.btnMultiplicacion.id->operador("*")
            binding.btnDivision.id->operador("/")

            binding.btnIgual.id->producto()
            binding.btnClear.id->limpiar()
        }
    }
    private fun agregarNumero(number:String){
        numeroActual+=number
        binding.resultado.text=numeroActual
    }
    private fun operador(operator:String){
        if(numeroActual.isNotEmpty()){

            primerNumber=numeroActual.toDouble()
            operador=operator
            numeroActual=""
        }
    }
    private fun producto(){
        if (numeroActual.isNotEmpty()) {
            val secondNumber = numeroActual.toDouble() // Convertir el segundo número
            val result = when (operador) {
                "+" -> primerNumber + secondNumber
                "-" -> primerNumber - secondNumber
                "*" -> primerNumber * secondNumber
                "/" -> if (secondNumber != 0.0) primerNumber / secondNumber else Double.NaN
                else -> 0.0
            }

            // Mostrar el resultado
            binding.resultado.text = result.toString()
            numeroActual = result.toString()
            operador = ""
        }
    }
    private fun limpiar(){
        numeroActual=""
        operador=""
        primerNumber=0.0
        binding.resultado.text="0"
    }
}