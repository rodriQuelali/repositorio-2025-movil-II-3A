package com.example.appcars.data.services

import com.example.appcars.data.model.LoginRequest
import com.example.appcars.data.model.LoginResponse
import com.example.appcars.model.cars.Car
import com.example.appcars.model.post.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("cars/")
    suspend fun getCars(): Response<List<Car>>


    @POST("auth/api/token/")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse


}