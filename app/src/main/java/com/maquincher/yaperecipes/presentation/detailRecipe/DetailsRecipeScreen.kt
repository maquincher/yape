package com.maquincher.yaperecipes.presentation.detailRecipe


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maquincher.yaperecipes.models.LatLng
import com.maquincher.yaperecipes.models.Recipe
import com.maquincher.yaperecipes.presentation.detailRecipe.components.cardRecipe
import com.maquincher.yaperecipes.presentation.detailRecipe.components.mapRecipe
import java.math.BigDecimal
import javax.inject.Inject


class DetailsRecipePage @Inject constructor(

) {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun create(
        navController: NavController,
        recipeId: Int
    ) {

        val detailsRecipeViewModel = hiltViewModel<DetailsRecipeViewModel>()
        val latlng: LatLng by detailsRecipeViewModel.latLng.observeAsState(
            initial = LatLng(
                lat = BigDecimal.ZERO,
                lng = BigDecimal.ZERO
            )
        )
        val recipe: Recipe by detailsRecipeViewModel.recipe.observeAsState(
            initial = Recipe(
                id = 0,
                name = "Sin nombre",
                latlng = latlng,
                description = "Sin descripcion",
                imgSrcUrl = ""
            )
        )

        val showMap: Boolean by detailsRecipeViewModel.showMap.observeAsState(initial = false)

        val nameFloatingButton: ImageVector by detailsRecipeViewModel.nameFloatingButton.observeAsState(
            initial = Icons.Outlined.LocationOn
        )

        detailsRecipeViewModel.getRecipesById(recipeId)
        Scaffold(
            topBar = {
                TopAppBar {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = " volver ",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                    {
                        Text(recipe.name, fontSize = 20.sp)
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { detailsRecipeViewModel.toggleMap() }) {
                    Icon(imageVector = nameFloatingButton, contentDescription = "")
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            )
            {
                cardRecipe(recipe)

                if (showMap) {
                    mapRecipe()
                }
            }
        }
    }
}
