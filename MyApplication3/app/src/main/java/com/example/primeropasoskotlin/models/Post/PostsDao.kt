package com.example.primeropasoskotlin.models.Post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.primeropasoskotlin.models.Posts

class PostsDao(private val postRepositori: PostsRepositori) {

    private val _posts = MutableLiveData<List<Posts>>()
    val posts: LiveData<List<Posts>> get() =  _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    
    //guardar
    private val _result = MutableLiveData<Boolean>()
    val result : LiveData<Boolean> get() =  _result 
    
    fun getPosts(){
        postRepositori.getAllPosts(
            callback = {postList -> _posts.value = postList},
            errorCallback ={thorwable -> _error.value = thorwable.message}
        )
    }

    fun getGuardar(posts: Posts){
        postRepositori.getGguardar(
            posts = posts,
            callback = {_result.value = true},
            errorCallback = {_result.value = false}
        )
    }
    
}