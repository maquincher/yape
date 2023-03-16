package com.maquincher.yaperecipes.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maquincher.yaperecipes.mockdataAndroidTest.LocalMockData
import com.maquincher.yaperecipes.models.Recipe
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
class HomeViewModelTest{

    @RelaxedMockK
    private lateinit var recipesServices: RecipesServices

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    val listRecipes = LocalMockData.getRecipes()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(recipesServices)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun whenHomeViewModelistCreated() = runTest {
        //given

        coEvery{ recipesServices.getRecipes("")} returns listRecipes

        //when
         homeViewModel.onSearchClick("")
        delay(1000)

        //then
        assert(homeViewModel.recipe.value == listRecipes)

    }


    @Test
    fun whenHomeViewModelistNull() = runTest {
        //given

        coEvery{ recipesServices.getRecipes("")} returns listRecipes.filter { it.name == "Prueba sin retorno" }

        //when
        homeViewModel.onSearchClick("")
        delay(1000)

        //then
        assert(homeViewModel.recipe.value == emptyList<Recipe>())

    }
}