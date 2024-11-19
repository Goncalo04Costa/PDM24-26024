package com.example.noticiasapi.app.data.remote.api

import com.example.noticiasapi.app.data.remote.model.NoticiaDetailDto
import com.example.noticiasapi.app.data.remote.model.NoticiaDto
import com.example.noticiasapi.app.domain.use_case.GetNoticiasUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitInstance{
    val api: NoticiasAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thenewsapi.com/v1/news/\"")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoticiasAPI::class.java)

    }
}


