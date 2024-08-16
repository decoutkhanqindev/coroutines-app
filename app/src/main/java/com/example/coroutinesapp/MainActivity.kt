package com.example.coroutinesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.clickToCountBtn.setOnClickListener {
            counter++
            binding.clickToCountText.text = counter.toString()
        }

        binding.clickToDownloadBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                demoDownload()
            }
        }
    }

    private suspend fun demoDownload() {
        for (i in 1..100000) {
            Log.d("TAGY", "Number of downloading $i in ${Thread.currentThread().name}")

            withContext(Dispatchers.Main) {
                binding.clickToDownloadText.text = i.toString()
            }
        }
    }
}