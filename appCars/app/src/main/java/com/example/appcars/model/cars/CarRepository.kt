package com.example.appcars.model.cars

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appcars.model.post.Post
import com.example.appcars.model.post.PostDataSource

class CarRepository(private val dataSource: CarDataSource) {
    private val _cars = MutableLiveData<List<Car>?>()
    val cars: LiveData<List<Car>?> get() = _cars

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    suspend fun getCars() {
        try {
            val carList = dataSource.getAllCars()
            Log.d("CarRepository", "Lista de autos recibida: $carList")
            _cars.postValue(carList)
        } catch (e: Exception) {
            _error.postValue(e.message)
            Log.e("CarRepository", "Error al obtener autos: ${e.message}")
        }
    }
}