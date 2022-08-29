package com.example.bindserviceexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class MyService : Service() {
    private val binder: IBinder= LocalBinderClass()
    private val randomNumber:Random= Random()
    override fun onBind(intent: Intent): IBinder {
       return binder

    }

    fun getNumber():Int{
        return randomNumber.nextInt(200)
    }

    class LocalBinderClass: Binder() {
        fun getService(): MyService{
            return MyService()
        }
    }
}