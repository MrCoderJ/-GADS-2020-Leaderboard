package com.example.gadproject.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.gadproject.api.DataBroker
import com.example.gadproject.api.Resource
import com.example.gadproject.api.RetrofitClient
import com.example.gadproject.db.GadsDB
import com.example.gadproject.db.TopLearnerCache
import com.example.gadproject.models.TopLearner
import com.example.gadproject.util.AppExecutors

class TopLearnersRepository(private val app: Application) {

    private val appExecutors = AppExecutors()
    private val webService = RetrofitClient.getClient(app.applicationContext)
    private val cache = TopLearnerCache(app)
    private val dao = GadsDB.getDb(app).hoursDao()

    fun loadHours(): LiveData<Resource<List<TopLearner>>> {
        return object : DataBroker<TopLearner>(app, appExecutors){
            override fun saveData(items: List<TopLearner>) {
                dao.save(items)
            }

            override fun shouldFetch(data: List<TopLearner>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<TopLearner>> = dao.fetchHours()

            override fun createRequest(): LiveData<Resource<List<TopLearner>>> = webService.fetchHours()

        }.asLiveData()
    }
}