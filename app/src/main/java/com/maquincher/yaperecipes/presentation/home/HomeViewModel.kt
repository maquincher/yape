package com.maquincher.yaperecipes.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.maquincher.yaperecipes.models.Recipe
import com.maquincher.yaperecipes.navigation.AppScreens
import com.maquincher.yaperecipes.recipes.RecipesServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recipesServices: RecipesServices
    ) : ViewModel() {

    private val _filter = MutableLiveData<String>()
    val filter: LiveData<String> = _filter

    private val _recipeId = MutableLiveData<Int>()
    val recipeId: LiveData<Int> = _recipeId

    private val _recipe = MutableLiveData<List<Recipe>>()
    val recipe: LiveData<List<Recipe>> = _recipe


    fun onFilterChanged(filter: String) {
        _filter.value = filter
    }

    fun onSearchClick(searchValue: String) {
        getRecipes(searchValue)
    }

    fun onClickRecipe(recipe: Recipe, navController:NavController) {
        navController.navigate(AppScreens.detailsRecipeScreen.route + "/${recipe.id}" )
    }

    init {
        getRecipes()
    }

    private fun getRecipes(search: String? = null) {
        viewModelScope.launch {
            try {
                recipesServices.getRecipes(search).let {
                    _recipe.value = it

                }
            } catch (e: Exception) {
                _recipe.value = emptyList()
            }
        }
    }


}