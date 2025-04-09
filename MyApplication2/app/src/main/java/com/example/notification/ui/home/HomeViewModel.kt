package com.example.notification.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienbenido a la pagina principal"
    }

    private val _textName = MutableLiveData<String>().apply {
        value = "Alan Brito"
    }
    val textName: LiveData<String> = _textName
    val text: LiveData<String> = _text
}