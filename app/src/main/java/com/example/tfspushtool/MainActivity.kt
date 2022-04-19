package com.example.tfspushtool

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tfspushtool.apiservice.PushTfsApiService
import com.example.tfspushtool.apiservice.TfsApi
import com.example.tfspushtool.apiservice.TfsData
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
        val tfsAddress = sharedPreferences.getString("tfsAddress", "")
        val token = sharedPreferences.getString("token", "")
        val assignedTo = sharedPreferences.getString("assignedTo", "")
        val backlogTitle = sharedPreferences.getString("backlogTitle", "")
        val iterationPath = sharedPreferences.getString("iterationPath", "")
        val areaPath = sharedPreferences.getString("areaPath", "")
        val workItemContent = sharedPreferences.getString("workItemContent", "")

        binding.tfsAddress.setText(tfsAddress)
        binding.token.setText(token)
        binding.assignedTo.setText(assignedTo)
        binding.backlogTitle.setText(backlogTitle)
        binding.iterationPath.setText(iterationPath)
        binding.areaPath.setText(areaPath)
        binding.workItemContent.setText(workItemContent)

    }

    private fun pushData() {
        var apiInstance = instance.create(PushTfsApiService::class.java)
        var sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE)
        var edit = sharedPreferences.edit()

        val tfsAddress = binding.tfsAddress.text.toString()
        val token = binding.token.text.toString()
        val assignedTo = binding.assignedTo.text.toString()
        val backlogTitle = binding.backlogTitle.text.toString()
        val iterationPath = binding.iterationPath.text.toString()
        val areaPath = binding.areaPath.text.toString()
        val workItemContent = binding.workItemContent.text.toString()

        edit.putString("tfsAddress",tfsAddress)
        edit.putString("token",token)
        edit.putString("assignedTo",assignedTo)
        edit.putString("backlogTitle",backlogTitle)
        edit.putString("iterationPath",iterationPath)
        edit.putString("areaPath",areaPath)
        edit.putString("workItemContent",workItemContent)

        edit.apply()

        val tfsData = TfsData(
            tfsAddress = tfsAddress,
            token = token,
            assignedTo = assignedTo,
            backlogTitle = backlogTitle,
            iterationPath = iterationPath,
            areaPath =  areaPath,
            workItemContent = workItemContent
        )

        GlobalScope.launch {
                val result = apiInstance.pushTFSUserDataToTfs(tfsData)
                Log.i("res is",result.toString());
            }
    }
}