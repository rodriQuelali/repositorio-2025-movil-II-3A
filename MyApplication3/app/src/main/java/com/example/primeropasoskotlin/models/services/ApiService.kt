package com.example.primeropasoskotlin.models.services

import com.example.primeropasoskotlin.models.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts():Call<List<Posts>>
    //mas empoints
    @GET("/buscuedaFecha")
    fun getFecha():Call<List<String>>

}