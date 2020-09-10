package com.example.gadproject.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val DEV_SERVER = "https://gadsapi.herokuapp.com/api/"

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun getAuthInterceptor(ctx: Context): Interceptor {
        return Interceptor { chain ->
            val headers = chain.request().headers.newBuilder().apply {
                add("Content-Type", "application/json")
            }.build()
            val request = chain.request().newBuilder().headers(headers).build()
            chain.proceed(request)
        }
    }

    private fun getOkHttpClient(ctx: Context): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).addInterceptor(
            getAuthInterceptor(ctx)
        ).connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES).build()
    }

    fun getClient(ctx: Context): RetrofitInterface {
        return Retrofit.Builder().baseUrl(DEV_SERVER).client(getOkHttpClient(ctx))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory()).build()
            .create(RetrofitInterface::class.java)
    }
}