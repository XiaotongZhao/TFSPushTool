package com.example.tfspushtool

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tfspushtool.databinding.ActivityMainBinding
import com.example.tfspushtool.network.MarsApi
import com.example.tfspushtool.network.PushTfsApiService
import com.example.tfspushtool.network.TFSUserData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var instance = MarsApi.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pushDataToTfs.setOnClickListener{ pushData() }
    }

    private fun pushData() {
        var apiInstance = instance.create(PushTfsApiService::class.java)
        val userInfo = TFSUserData(
            userName =  binding.userName.text.toString(),
            tfsAddress =  binding.tfsAddress.text.toString(),
            tokenInformation =  binding.tokenInformation .text.toString(),
            message = binding.message.text.toString()
        )

        GlobalScope.launch {
                val result = apiInstance.pushTFSUserDataToTfs(userInfo)
                Log.i("res is",result.toString());
            }
    }
}