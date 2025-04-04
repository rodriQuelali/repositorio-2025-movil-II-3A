package com.example.appcars.model.cars

import android.content.Context
import com.example.appcars.data.services.ApiClient
import com.example.appcars.data.services.ApiService
import com.example.appcars.model.post.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CarDataSource(private val context: Context) {
    private val apiService: ApiService = ApiClient.create(context)

    suspend fun getAllCars(): List<Car>? {
        return try {
            val response = apiService.getCars()
            if (response.isSuccessful) {
                response.body()
            } else {
                throw Exception("Error: ${response.code()}")
            }
        } catch (e: Exception) {
            throw IOException("Error al obtener Cars", e)
        }
    }
}