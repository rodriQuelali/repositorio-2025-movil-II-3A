package com.example.primeropasoskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.primeropasoskotlin.models.Post.PostsDao
import com.example.primeropasoskotlin.models.Post.PostsRepositori
import com.example.primeropasoskotlin.models.Posts

class PostViewModel(aplication:Application):AndroidViewModel(aplication) {

    private val postsDao:PostsDao

    val post:LiveData<List<Posts>> get() = postsDao.posts
    val error:LiveData<String> get() = postsDao.error

    init {
        val postsRepositori = PostsRepositori(aplication)
        postsDao = PostsDao(postsRepositori)
    }

    fun getPost(){
        postsDao.getPosts()
    }
}