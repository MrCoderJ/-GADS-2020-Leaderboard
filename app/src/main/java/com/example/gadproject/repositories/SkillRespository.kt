package com.example.gadproject.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.gadproject.api.*
import com.example.gadproject.db.GadsDB
import com.example.gadproject.models.Skills
import com.example.gadproject.db.SkillsCache
import com.example.gadproject.util.AppExecutors

class SkillRespository(private val app: Application){

    private val appExecutors = AppExecutors()
    private val webService = RetrofitClient.getClient(app.applicationContext)
    private val cache = SkillsCache(app)
    private val dao = GadsDB.getDb(app).skillDao()


    fun loadSkills(): LiveData<Resource<List<Skills>>> {
        return object : DataBroker<Skills>(app, appExecutors){
            override fun saveData(items: List<Skills>) {
                dao.save(items)
            }

            override fun shouldFetch(data: List<Skills>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Skills>> = dao.fetchSkills()

            override fun createRequest(): LiveData<Resource<List<Skills>>> = webService.fetchSkills()

        }.asLiveData()
    }




}

