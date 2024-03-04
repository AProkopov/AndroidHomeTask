package com.antonprokopov.breedsfeed.data.api

import com.antonprokopov.breedsfeed.data.models.BreedListResponse
import com.antonprokopov.breedsfeed.data.models.PhotoDto
import retrofit2.http.GET

interface ApiService {


    @GET("/photos")
    suspend fun getPhotos(): List<PhotoDto>

    @GET("/api/breeds/list/all")
    suspend fun getBreeds(): BreedListResponse
}