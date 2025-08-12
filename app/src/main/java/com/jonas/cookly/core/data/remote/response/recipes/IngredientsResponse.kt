package com.jonas.cookly.core.data.remote.response.recipes

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String
)
