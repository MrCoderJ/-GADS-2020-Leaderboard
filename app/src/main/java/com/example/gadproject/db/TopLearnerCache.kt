package com.example.gadproject.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.gadproject.models.TopLearner
import com.example.gadproject.util.AppExecutors

class TopLearnerCache(app: Application) {

    private val hoursDao = GadsDB.getDb(app).hoursDao()
    private val appExecutors = AppExecutors()


    fun insert(hours: List<TopLearner>, done: () -> Unit){
        appExecutors.diskIO().execute {
            Log.d("HourCache", "Inserting ${hours.size} Hours")
            hoursDao.save(hours)
            done()
        }
    }

    fun fetchHours(): LiveData<List<TopLearner>>{
        return  hoursDao.fetchHours()
    }
}