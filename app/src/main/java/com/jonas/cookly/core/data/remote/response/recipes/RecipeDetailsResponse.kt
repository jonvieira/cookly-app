package com.jonas.cookly.core.data.remote.response.recipes

import com.google.gson.annotations.SerializedName

data class RecipeDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("preparationTime")
    val preparationTime: Int,
    @SerializedName("preparationMode")
    val preparationMode: Int,
    @SerializedName("createAt")
    val createAt: Int,
    @SerializedName("ingredients")
    val ingredients: List<IngredientsResponse>
)
