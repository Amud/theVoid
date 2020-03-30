package com.amud.thevoid

import android.app.Application

class AppController : Application() {



    companion object{
        private lateinit var mInstance: AppController

        fun getInstance(): AppController {
            return mInstance
        }

    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this

    }




}