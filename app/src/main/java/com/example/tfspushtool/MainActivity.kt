package com.example.tfspushtool

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tfspushtool.apiservice.PushTfsApiService
import com.example.tfspushtool.apiservice.TFSUserData
import com.example.tfspushtool.apiservice.TfsApi
import com.example.tfspushtool.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var instance = TfsApi.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        binding.pushDataToTfs.setOnClickListener{ pushData() }
    }

    private  fun initData() {
        var sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "")
        val tfsAddress = sharedPreferences.getString("tfsAddress", "")
        val tokenInformation = sharedPreferences.getString("tokenInformation", "")
        val message = sharedPreferences.getString("message", "")
        binding.userName.setText(userName)
        binding.tfsAddress.setText(tfsAddress)
        binding.tokenInformation.setText(tokenInformation)
        binding.message.setText(message)

    }

    private fun pushData() {
        var apiInstance = instance.create(PushTfsApiService::class.java)
        var sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
        var edit = sharedPreferences.edit()

        val userName = binding.userName.text.toString()
        val tfsAddress = binding.tfsAddress.text.toString()
        val tokenInformation = binding.tokenInformation.text.toString()
        val message = binding.message.text.toString()

        edit.putString("userName",userName)
        edit.putString("tfsAddress",tfsAddress)
        edit.putString("tokenInformation",tokenInformation)
        edit.putString("message",message)
        edit.apply()

        val userInfo = TFSUserData(
            userName =  userName,
            tfsAddress =  tfsAddress,
            tokenInformation =  tokenInformation,
            message = message
        )

        GlobalScope.launch {
                val result = apiInstance.pushTFSUserDataToTfs(userInfo)
                Log.i("res is",result.toString());
            }
    }
}