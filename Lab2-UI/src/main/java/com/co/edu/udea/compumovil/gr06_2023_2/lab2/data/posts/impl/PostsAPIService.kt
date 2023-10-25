package com.co.edu.udea.compumovil.gr06_2023_2.lab2.data.posts.impl


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class APIResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<Post>,
){
    constructor() : this("", 0, listOf(Post(Source("",""),"","","","", "", "", "",)))

    fun clear() {
        status = ""
        totalResults  = 0
        articles  = listOf(Post(Source("",""),"","","","", "", "", "",))
    }

    fun set(posts: APIResponse) {
        status = posts.status
        totalResults  = posts.totalResults
        articles  = posts.articles
    }
}

data class Post(
    val source: Source,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

data class Source(
    val id: String? = null,
    val name: String? = null,
)

const val POSTS_BASE_URL = "https://newsapi.org/v2/"
interface PostsAPIService {
    @GET("everything?apiKey=bcd4fec20d61437c9f992d0b2df61a34?")
    suspend fun getPosts(): APIResponse

    companion object {
        var postsApiService: PostsAPIService? = null
        fun getPostsAPIInstance(): PostsAPIService {
            if (postsApiService == null) {
                postsApiService = Retrofit.Builder()
                    .baseUrl(POSTS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PostsAPIService::class.java)
            }
            return postsApiService!!
        }
    }
}