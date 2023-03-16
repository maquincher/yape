package com.maquincher.yaperecipes.recipes

import com.maquincher.yaperecipes.models.Recipe
import retrofit2.http.GET
import retrofit2.http.Query


interface RecipesApi {
    @GET("/recipes")
    suspend fun getRecipes(@Query("search") search:String?): List<Recipe>

    @GET("/recipes")
    suspend fun getRecipesById(@Query("id") search:Int?): Recipe
}
