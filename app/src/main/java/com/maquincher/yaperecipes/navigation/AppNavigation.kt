package com.maquincher.yaperecipes.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maquincher.yaperecipes.presentation.Main
import com.maquincher.yaperecipes.presentation.detailRecipe.DetailsRecipePage
import com.maquincher.yaperecipes.presentation.home.HomePage
import com.maquincher.yaperecipes.presentation.splash.SplashScreen
import javax.inject.Inject

class AppNavigation
@Inject constructor(
    private val main: Main,
    private val homePage: HomePage,
    private val detailsRecipePage: DetailsRecipePage,

    ) {


    @Composable
    fun create() {
        val navController = rememberNavController()

        val startDestination = AppScreens.splashScreen.route
        NavHost(navController = navController, startDestination = startDestination) {

            runCatching {
                composable(route = AppScreens.splashScreen.route) {
                    SplashScreen(navController = navController)
                }

                composable(route = AppScreens.homeScreen.route) {
                    main.create(
                        navController = navController,
                        display = { homePage.create(navController = navController) }
                    )
                }

                composable(
                    route = AppScreens.detailsRecipeScreen.route + "/{recipeId}",
                    arguments = listOf(navArgument(name = "recipeId") {
                        type = NavType.IntType
                    })
                ) {
                    it.arguments?.getInt("recipeId")?.let {
                        detailsRecipePage.create(
                            navController = navController, recipeId = it
                        )
                    }
                }
            }.getOrThrow()


        }
    }
}