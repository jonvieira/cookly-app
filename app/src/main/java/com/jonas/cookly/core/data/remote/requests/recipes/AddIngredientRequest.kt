package com.jonas.cookly.core.data.remote.requests.recipes

import com.google.gson.annotations.SerializedName

data class AddIngredientRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String
)
