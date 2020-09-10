package com.example.gadproject.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.gadproject.api.Resource
import com.example.gadproject.models.TopLearner
import com.example.gadproject.repositories.TopLearnersRepository

class TopLearnersViewModel(app: Application): ViewModel() {

    private val repository = TopLearnersRepository(app)
    private val queryLiveData = MutableLiveData<String>()
    private val result: LiveData<LiveData<Resource<List<TopLearner>>>> = Transformations.map(queryLiveData){
        repository.loadHours()
    }

    val topLearner: LiveData<Resource<List<TopLearner>>> = Transformations.switchMap(result){it}
    val networkErrors: LiveData<Resource<List<TopLearner>>> = Transformations.switchMap(result){it}
    val loadingState : LiveData<Resource<List<TopLearner>>> = Transformations.switchMap(result){it}

    fun fetchData(){
        queryLiveData.postValue("fetch")
    }
}