package com.example.tfspushtool

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.example.tfspushtool.databinding.ActivityMainBinding
import com.example.tfspushtool.network.MarsApi
import com.example.tfspushtool.network.MarsApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var instance = MarsApi.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pushDataToTfs.setOnClickListener{ pushDataToTFS() }
    }

    fun pushDataToTFS() {
        var tfsAddress = binding.tfsAddress.text.toString();
        var tokenInformation = binding.tokenInformation .text.toString();
        var userName = binding.userName.text.toString();
        var message =binding.message.text.toString();

            var apiInstance = instance.create(MarsApiService::class.java)

            GlobalScope.launch {
                val result = apiInstance.getPhotos()
                Log.i("test",result);

            }


            Log.i("http","this is a test");

    }
}