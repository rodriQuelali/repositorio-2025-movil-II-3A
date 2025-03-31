package com.example.appcars.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcars.model.post.Post
import com.example.appcars.model.post.PostRepository

class HomeViewModel(private val postRepository: PostRepository) : ViewModel() {


    val getAll: LiveData<List<Post>?> get() = postRepository.posts
    val error:LiveData<String> get() = postRepository.error

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getPost(){
        postRepository.getPosts()
    }


}