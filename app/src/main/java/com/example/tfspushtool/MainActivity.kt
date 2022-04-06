package com.example.tfspushtool

import androidx.appcompat.app.AppCompatActivity
import com.example.tfspushtool.databinding.ActivityMainBinding
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

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
    }
}