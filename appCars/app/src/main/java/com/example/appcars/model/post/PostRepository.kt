package com.example.appcars.model.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepository(private val dataSource: PostDataSource) {


    private val _posts = MutableLiveData<List<Post>?>()
    val posts: LiveData<List<Post>?> get() =  _posts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPosts(){
        dataSource.getAllPosts(
            callback = {postList -> _posts.postValue(postList)},
            errorCallback ={throwable -> _error.postValue(throwable.message)}
        )
    }
}