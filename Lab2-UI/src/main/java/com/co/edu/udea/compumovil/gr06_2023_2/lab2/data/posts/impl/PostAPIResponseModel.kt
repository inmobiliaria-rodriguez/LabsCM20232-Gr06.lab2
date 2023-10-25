package com.co.edu.udea.compumovil.gr06_2023_2.lab2.data.posts.impl;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostAPIResponseModel : ViewModel() {

    private val _apiResponse = APIResponse()
    var errorMessage: String by mutableStateOf("Mi rey, pasaron cositas y fallo")
    val postsList: List<Post>
        get() = _apiResponse.articles

    fun getPostsList() {
        viewModelScope.launch {
            val apiService = PostsAPIService.getPostsAPIInstance()
            try {
                _apiResponse.clear()
                _apiResponse.set(apiService.getPosts())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

