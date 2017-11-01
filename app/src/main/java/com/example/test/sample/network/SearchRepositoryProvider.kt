package com.example.test.sample.network

import com.example.test.sample.network.GithubApiService
import com.example.test.sample.network.SearchRepository

/**
 * Created by sumon.chatterjee on 01/11/17.
 */
object SearchRepositoryProvider{

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubApiService.create())
    }
}