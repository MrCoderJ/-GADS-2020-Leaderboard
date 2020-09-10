package com.example.gadproject.api

import androidx.lifecycle.LiveData
import com.example.gadproject.models.TopLearner
import com.example.gadproject.models.Skills
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {



    @GET("/api/skilliq")
    fun fetchSkills(): LiveData<Resource<List<Skills>>>

    @GET("/api/hours")
    fun fetchHours(): LiveData<Resource<List<TopLearner>>>


    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun postResponse(
        @Field("entry.1824927963")emailAddress:String,
        @Field("entry.1877115667")firstName:String,
        @Field("entry.2006916086")lastName:String,
        @Field("entry.284483984")linkToProject:String
    ) : Call<Void>
}