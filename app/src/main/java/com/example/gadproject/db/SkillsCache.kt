package com.example.gadproject.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.gadproject.models.Skills
import com.example.gadproject.util.AppExecutors

class SkillsCache(app: Application) {
    private val skillsDao = GadsDB.getDb(app).skillDao()
    private val appExecutors = AppExecutors()


    fun insert(skills: List<Skills>, done: () -> Unit){
        appExecutors.diskIO().execute{
            Log.d("SkillCache", "Inserting ${skills.size} skills")
            skillsDao.save(skills)
            done()
        }
    }

    fun fetchSkills(): LiveData<List<Skills>>{
        return skillsDao.fetchSkills()
    }

}