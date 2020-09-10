package com.example.gadproject.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.gadproject.api.Resource
import com.example.gadproject.models.Skills
import com.example.gadproject.repositories.SkillRespository

class SkillsViewModel(app: Application): ViewModel(){

    private val repository = SkillRespository(app)
    private val queryLiveData = MutableLiveData<String>()
    private val result: LiveData<LiveData<Resource<List<Skills>>>> = Transformations.map(queryLiveData){
        repository.loadSkills()
    }
    lateinit var skill: LiveData<Resource<List<Skills>>>

    fun fetchSkills(){
        skill = repository.loadSkills()
    }

    val skills: LiveData<Resource<List<Skills>>> = Transformations.switchMap(result){it}
    val networkErrors: LiveData<Resource<List<Skills>>> = Transformations.switchMap(result){it}
    val loadingState : LiveData<Resource<List<Skills>>> = Transformations.switchMap(result){it}

    fun fetchData(){
        queryLiveData.postValue("fetch")
    }
}