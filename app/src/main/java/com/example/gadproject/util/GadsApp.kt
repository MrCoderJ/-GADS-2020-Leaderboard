package com.example.gadproject.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GadsApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }


    companion object{
        fun getViewModelFactory(app: Application): ViewModelProvider.Factory{
            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return modelClass.getConstructor(Application::class.java).newInstance(app)
                }

            }
        }
    }
}