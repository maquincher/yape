package com.maquincher.yaperecipes.recipes

import com.maquincher.yaperecipes.models.Recipe
import javax.inject.Inject

interface RecipesServices {
    suspend fun getRecipes(search: String?): List<Recipe>
    suspend fun getRecipeById(recipeId: Int): Recipe?
}

class DefaultRecipesServices @Inject constructor(
    private val recipesApi: RecipesApi,
    private val recipesApiLocal: RecipesApiLocal
) : RecipesServices {
    override suspend fun getRecipes(search: String?): List<Recipe> = runCatching {
        recipesApi.getRecipes(search).let {
            it
        }
    }.fold(
        onSuccess = { it },
        onFailure = { recipesApiLocal.getRecipes(search) }
    )

    override suspend fun getRecipeById(recipeId: Int): Recipe? = runCatching {
        recipesApi.getRecipesById(recipeId)
    }.fold(
        onSuccess = { it },
        onFailure = { recipesApiLocal.getRecipesById(recipeId) }
    )
}