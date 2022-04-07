package com.example.tfspushtool

import androidx.appcompat.app.AppCompatActivity
import com.example.tfspushtool.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.tfspushtool.network.MarsApi
import com.example.tfspushtool.overview.OverviewViewModel

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

        try {
            val queue = Volley.newRequestQueue(this)
            var test = ""
            val url = "https://android-kotlin-fun-mars-server.appspot.com/"
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    test = response
                    Log.i("http",response);
                },
                Response.ErrorListener {   })
        } catch (e: Exception) {
            var ex = e;
        }
    }
}