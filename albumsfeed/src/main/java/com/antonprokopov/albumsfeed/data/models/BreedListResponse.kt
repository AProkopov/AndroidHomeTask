package com.antonprokopov.albumsfeed.data.models

import com.google.gson.annotations.SerializedName

data class BreedListResponse(
    @SerializedName("message")
    val message: Map<String, List<String>>,
    @SerializedName("status")
    val status: String
) {
    fun toBreedsListModel() = BreedsListModel(breeds = message.keys.toList())
}