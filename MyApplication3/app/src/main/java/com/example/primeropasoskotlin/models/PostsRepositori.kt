package com.example.primeropasoskotlin.models

import com.example.primeropasoskotlin.models.services.ApiClient
import retrofit2.Response
import retrofit2.Retrofit

class PostsRepositori {


     fun getAllPosts(): Response<MutableList<Posts>> {
        val response = ApiClient.instance.getPosts()
         return response
    }
}