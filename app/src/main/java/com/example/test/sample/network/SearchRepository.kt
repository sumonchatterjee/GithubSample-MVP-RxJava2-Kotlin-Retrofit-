package com.example.test.sample.network

import com.example.test.sample.network.GithubApiService
import com.google.gson.JsonObject

/**
 * Created by sumon.chatterjee on 01/11/17.
 */
class SearchRepository (val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String): io.reactivex.Observable<JsonObject> {
        return apiService.search(query = "location:$location language:$language")
    }
}