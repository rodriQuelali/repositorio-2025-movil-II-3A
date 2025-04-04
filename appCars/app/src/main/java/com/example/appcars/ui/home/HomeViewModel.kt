package com.example.appcars.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcars.model.cars.Car
import com.example.appcars.model.cars.CarRepository
import com.example.appcars.model.post.Post
import com.example.appcars.model.post.PostRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val carRepository: CarRepository) : ViewModel() {


    val getAll: LiveData<List<Car>?> get() = carRepository.cars
    val error:LiveData<String> get() = carRepository.error

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getCars() {
        viewModelScope.launch {
            carRepository.getCars()
        }
    }


}