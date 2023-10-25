/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.co.edu.udea.compumovil.gr06_2023_2.lab2.model

import com.co.edu.udea.compumovil.gr06_2023_2.lab2.data.posts.impl.Post
import com.co.edu.udea.compumovil.gr06_2023_2.lab2.data.posts.impl.PostAPIResponseModel
import com.co.edu.udea.compumovil.gr06_2023_2.lab2.data.posts.impl.Source

/**
 * A container of [Post]s, partitioned into different categories.
 */
data class PostsFeed(
    var highlightedPost: Post,
    var recommendedPosts: List<Post>
) {
    /**
     * Returns a flattened list of all posts contained in the feed.
     */
    constructor() : this(Post(Source("",""),"","","","", "", "", "",),listOf(Post(Source("",""),"","","","", "", "", "",)))

    init {
        val postAPIReponse  = PostAPIResponseModel()
        postAPIReponse.getPostsList()
        highlightedPost = postAPIReponse.postsList.get(0)
        recommendedPosts = postAPIReponse.postsList.drop(1)
    }

    val allPosts: List<Post> =
        listOf(highlightedPost) + recommendedPosts
}
