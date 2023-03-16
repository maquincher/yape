package com.maquincher.yaperecipes.presentation.detailRecipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maquincher.yaperecipes.mockdataAndroidTest.LocalMockData
import com.maquincher.yaperecipes.presentation.home.HomeViewModel
import com.maquincher.yaperecipes.recipes.RecipesServices
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class DetailsRecipeViewModelTest{
    @RelaxedMockK
    private lateinit var recipesServices: RecipesServices

    private lateinit var detailsRecipeViewModel: DetailsRecipeViewModel

    val listRecipes = LocalMockData.getRecipes()

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailsRecipeViewModel = DetailsRecipeViewModel(recipesServices)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }


    @Test
    fun whenShowMap() = runTest {
        //given

        coEvery{ recipesServices.getRecipeById(1)} returns listRecipes.find { it.id == 1 }

        //when
        detailsRecipeViewModel.toggleMap()
        delay(1000)

        //then
        assert(detailsRecipeViewModel.showMap.value == true)
        //assert(detailsRecipeViewModel.latLng.value?.lat == (-12.15317863007613).toBigDecimal())


        detailsRecipeViewModel.toggleMap()
        delay(1000)

        assert(detailsRecipeViewModel.showMap.value == false)

    }

    @Test
    fun verifyLatLng() = runTest {
        //given

        coEvery{ recipesServices.getRecipeById(1)} returns listRecipes.find { it.id == 1 }

        //when
        detailsRecipeViewModel.getRecipesById(1)
        delay(1000)

        //then
        assert(detailsRecipeViewModel.recipe.value?.latlng ==  listRecipes.find { it.id == 1 }?.latlng)
        //assert(detailsRecipeViewModel.latLng.value?.lat == (-12.15317863007613).toBigDecimal())




    }
}