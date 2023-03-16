package com.maquincher.yaperecipes.presentation.detailRecipe

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maquincher.yaperecipes.models.LatLng
import com.maquincher.yaperecipes.models.Recipe
import com.maquincher.yaperecipes.recipes.RecipesServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsRecipeViewModel @Inject constructor(
    private val recipesServices: RecipesServices
) : ViewModel() {

    private val _recipe = MutableLiveData<Recipe>()
    val recipe: LiveData<Recipe> = _recipe

    private val _latlng = MutableLiveData<LatLng>()
    val latLng: LiveData<LatLng> = _latlng

    private val _nameFloatingButton = MutableLiveData<ImageVector>()
    val nameFloatingButton: LiveData< ImageVector> = _nameFloatingButton

    private val _showMap = MutableLiveData<Boolean>()
    val showMap: LiveData<Boolean> = _showMap


    fun getRecipesById(recipeId: Int = 0) {
        viewModelScope.launch {
            try {
                recipesServices.getRecipeById(recipeId).let {
                    _recipe.value = it
                    _latlng.value = it?.latlng
                }
            } catch (e: Exception) {
                _recipe.value = null
            }
        }
    }

    fun toggleMap() {
        _showMap.value = when (_showMap.value) {
            true -> {
                _nameFloatingButton.value = Icons.Sharp.Star
                false
            }
            else -> {
                _nameFloatingButton.value = Icons.Sharp.Close
                true

            }
        }
    }

}