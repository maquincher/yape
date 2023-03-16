package com.maquincher.yaperecipes.recipes

import com.maquincher.yaperecipes.models.Recipe
import com.maquincher.yaperecipes.data.recipesData
import javax.inject.Inject


open class RecipesApiLocal @Inject constructor() : RecipesApi {
    override suspend fun getRecipes(search: String?): List<Recipe> = run {
        recipesData.filter { recipe ->
            try {
                search?.let { it1 ->
                    (
                        recipe.name.uppercase().contains(it1.uppercase())
                        ||
                        (recipe.ingredients.any {
                            it.uppercase().contains(it1.uppercase())
                        })
                    )
                }.let {
                    it == true
                }
            } catch (e: Exception) {
                false
            }
        }.ifEmpty { recipesData }
    }

    override suspend fun getRecipesById(search: Int?): Recipe =
        recipesData.find { search == it.id }!!


}
