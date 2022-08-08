package com.rezende.calculadoraimc

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.rezende.calculadoraimc.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCalcular.setOnClickListener {
            val vp = binding.peso

            val va = binding.altura
            val resultado = binding.resposta

            val vpeso = java.lang.Double.parseDouble(vp.text.toString())
            val valtura = java.lang.Double.parseDouble(va.text.toString())

            val imc = vpeso / (valtura * valtura)
            val valorFormatado = DecimalFormat("#,##0.00").format(imc)



            when {

                imc <= 18.5 -> {
                    resultado.setText("esta abaixo do peso com o imc de $valorFormatado")
                    resultado.setTextColor(getColor(R.color.abaixo))
                }
                imc in 18.6..24.9 -> {
                    resultado.setText("esta no peso ideal (PARABÉNS) com o imc $valorFormatado")
                    resultado.setTextColor(getColor(R.color.ideial))
                }
                imc in 25.00..29.9 -> {
                    resultado.setText("esta levemente acima do peso com o imc %.2f $valorFormatado")
                    resultado.setTextColor(getColor(R.color.acima))
                }
                imc in 30.0..34.9 -> {
                    resultado.setText("esta em obesidade nivel I com imc de $valorFormatado")
                    resultado.setTextColor(getColor(R.color.obs1))
                }
                imc in 35.0..39.9 -> {
                    resultado.setText("esta em obesidade II (SEVERA) com o imc de $valorFormatado")
                    resultado.setTextColor(getColor(R.color.obs2))
                }
                imc >= 40.00 -> {
                    resultado.setText("esta em obesidade III (MORBIDA) com o imc de $valorFormatado")
                    resultado.setTextColor(getColor(R.color.obs3))
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.refresh -> {

           var resetEditPeso = binding.peso
            var resetEditAltura = binding.altura
              var resetResposta = binding.resposta

               resetEditPeso.setText("")
               resetEditAltura.setText("")
               resetResposta.setText("")

           }
       }

        return super.onOptionsItemSelected(item)
    }
}