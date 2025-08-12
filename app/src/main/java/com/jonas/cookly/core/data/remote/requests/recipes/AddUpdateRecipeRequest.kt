package com.jonas.cookly.core.data.remote.requests.recipes

import com.google.gson.annotations.SerializedName
import com.jonas.cookly.core.data.remote.response.recipes.IngredientsResponse

data class AddUpdateRecipeRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("preparationTime")
    val preparationTime: Int,
    @SerializedName("preparationMode")
    val preparationMode: Int,
    @SerializedName("ingredients")
    val ingredients: List<AddIngredientRequest> = listOf()
)