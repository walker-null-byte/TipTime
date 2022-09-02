package com.example.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{calculateTip()}
    }
    private fun calculateTip(){
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        val percentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.fifteen_percent -> 0.15
            R.id.eighteen_percent -> 0.18
            else -> 0.20
        }
        if (cost==null){
            binding.tipAmount.text=""
            return
        }
        var tip = cost*percentage
        if (binding.roundUpSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount,formattedTip)

    }
}