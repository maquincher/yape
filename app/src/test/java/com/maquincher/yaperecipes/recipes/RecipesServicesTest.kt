package com.maquincher.yaperecipes.recipes

import com.maquincher.yaperecipes.mockdata.LocalMockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class RecipesApiService {


    @RelaxedMockK
    private lateinit var recipesApi: RecipesApi

    @RelaxedMockK
    private lateinit var recipesApiLocal: RecipesApiLocal


    private lateinit var recipesServices: RecipesServices


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        recipesServices = DefaultRecipesServices(recipesApi, recipesApiLocal)
    }


    @Test
    fun `when get Recipes return empty list `() = runBlocking {

        //GIVEN
        coEvery { recipesApi.getRecipes("dddd") } returns emptyList()

        //when
        recipesServices.getRecipes("dddd")

        //then
        coVerify(exactly = 1) { recipesApi.getRecipes("dddd") }


    }

    @Test
    fun `when get Recipes return  list with data `() = runBlocking {
        //given
        val listRecipes = LocalMockData.getRecipes()
        coEvery { recipesApi.getRecipes("") } returns listRecipes

        //when
        val recipes = recipesServices.getRecipes("")

        //then
        coVerify(exactly = 1) { recipesApi.getRecipes("")  }
        assert(listRecipes == recipes)
    }

    @Test
    fun `when get Recipe return list with name primera `() = runBlocking {
        //given
        val listRecipes = LocalMockData.getRecipes().filter { it.name == "primera" }
        coEvery { recipesApi.getRecipes("primera") } returns listRecipes.filter { it.name == "primera" }

        //when
        val recipes = recipesServices.getRecipes("primera")

        println(listRecipes.size)
        println(recipes.size)
        //then
        coVerify(exactly = 1) { recipesApi.getRecipes("primera")  }
        assert(listRecipes == recipes)
    }


}