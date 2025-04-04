package com.example.primeropasoskotlin.models.services

import com.example.primeropasoskotlin.models.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getPosts():Call<List<Posts>>
    //mas empoints
    @GET("/buscuedaFecha")
    fun getFecha():Call<List<String>>

    //guardar datos
    @POST("posts")
    fun getGuardar(@Body posts: Posts):Call<Posts>


    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id: Int, @Body posts: Posts): Call<Posts>

}