package com.jonas.cookly.core.data.remote.response.recipes

import com.google.gson.annotations.SerializedName
import com.jonas.cookly.core.domain.RecipesResponseModel

data class RecipesResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("ownerName")
    val ownerName: String? = null,
    @SerializedName("totalIngredients")
    val totalIngredients: Int,
    @SerializedName("preparationTime")
    val preparationTime: Int
)

fun RecipesResponse.toRecipesResponseModel() = RecipesResponseModel(
    id = id,
    name = name,
    category = category,
    ownerName = ownerName,
    totalIngredients = totalIngredients,
    preparationTime = preparationTime
)
