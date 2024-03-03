package com.antonprokopov.albumsfeed.data.api

import com.antonprokopov.albumsfeed.data.models.BreedListResponse
import com.antonprokopov.albumsfeed.data.models.PhotoDto
import retrofit2.http.GET

interface ApiService {


    @GET("/photos")
    suspend fun getPhotos(): List<PhotoDto>

    @GET("/api/breeds/list/all")
    suspend fun getBreeds(): BreedListResponse
}