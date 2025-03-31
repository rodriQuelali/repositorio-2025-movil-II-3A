package com.example.appcars.model.post

import android.content.Context
import com.example.appcars.data.Result
import com.example.appcars.data.services.ApiClient
import com.example.appcars.data.services.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PostDataSource (private val context: Context){
    private val apiService: ApiService = ApiClient.create(context) // Asegúrate de pasar el contexto

    fun getAllPosts(callback: (List<Post>?)-> Unit, errorCallback: (Throwable)-> Unit){
        try {

            apiService.getPosts().enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if(response.isSuccessful){
                        callback(response.body())
                    }else{
                        errorCallback(Exception("Error: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    errorCallback(t)
                }

            })

        }catch (e: Throwable){
            errorCallback(IOException("Error al obtener posts", e))
        }

    }
}