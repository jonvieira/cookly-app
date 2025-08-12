package com.jonas.cookly.core.domain

data class RecipesResponseModel(
    val id: String,
    val name: String,
    val category: String,
    val ownerName: String? = null,
    val totalIngredients: Int,
    val preparationTime: Int
)
