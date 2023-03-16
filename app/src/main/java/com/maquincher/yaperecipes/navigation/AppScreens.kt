package com.maquincher.yaperecipes.navigation

sealed class AppScreens(val route:String){
    object splashScreen : AppScreens("splash_screen")
    object homeScreen : AppScreens("home_screen")
    object detailsRecipeScreen : AppScreens("details_recipe_screen")

}
