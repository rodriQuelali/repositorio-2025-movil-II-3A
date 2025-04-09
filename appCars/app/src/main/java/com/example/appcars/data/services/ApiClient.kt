package com.example.appcars.data.services

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "http://3.86.202.19:8000/"

    // Instancia única de ApiService
    private var apiService: ApiService? = null

    fun create(context: Context): ApiService {
        println("empit -------------------------------mnmnmnmnmn")
        // Verifica si la instancia ya existe
        if (apiService == null) {
            println("empit 222222 -------------------------------mnmnmnmnmn")
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context)) // Agregar el interceptor
                .build()
            println("empit 222222 $okHttpClient -------------------------------mnmnmnmnmn")
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient) // Usa el cliente con el interceptor
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            println("empit 222222 $retrofit -------------------------------mnmnmnmnmn")
            apiService = retrofit.create(ApiService::class.java) // Crea la instancia
        }
        return apiService!! // Devuelve la instancia existente
    }
}