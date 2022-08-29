package com.example.bindserviceexample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.TextView
import com.example.bindserviceexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var myService:MyService
    var isBound=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, MyService::class.java)
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE)

        binding.button.setOnClickListener {
            binding.textView.text = myService.getNumber().toString()
        }
    }
    private val serviceConnection: ServiceConnection= object:ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder= MyService.LocalBinderClass()
            myService= binder.getService()
            isBound= true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound=false
        }

    }
}