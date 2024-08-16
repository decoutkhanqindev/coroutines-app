package com.example.coroutinesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesapp.databinding.ActivitySecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Default).launch {
            Log.d("TAGY1", "The App Started")

            val firstDeferred = async { generateFirstNumber() }
            val secondDeferred = async { generateSecondNumber() }

            val firstNumber = firstDeferred.await() // Lưu kết quả vào biến
            val secondNumber = secondDeferred.await()
            val result = firstNumber + secondNumber

            withContext(Dispatchers.Main) {
                binding.firstNumberText.text = firstNumber.toString() // Sử dụng biến đã lưu
                binding.secondNumberText.text = secondNumber.toString()
                binding.resultNumberText.text = result.toString()
            }
        }
    }

    private suspend fun generateFirstNumber(): Int {
        delay(5000)
        Log.d("TAGY1", "generateFirstNumber is DONE")
        return 5
    }

    private suspend fun generateSecondNumber(): Int {
        delay(7000)
        Log.d("TAGY1", "generateSecondNumber is DONE")
        return 4
    }
}