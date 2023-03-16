package com.maquincher.yaperecipes.presentation.detailRecipe.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.maquincher.yaperecipes.presentation.detailRecipe.DetailsRecipeViewModel


@Composable
fun mapRecipe( modifier: Modifier = Modifier) {

    val detailsRecipeViewModel = hiltViewModel<DetailsRecipeViewModel>()

    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            val mapProperties = MapProperties(
                isMyLocationEnabled = false
            )

            val latLng = detailsRecipeViewModel.latLng
            val singapore = LatLng(latLng.value?.lat?.toDouble()!!, latLng.value?.lng?.toDouble()!!)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(singapore, 15f)
            }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                properties = mapProperties,
                cameraPositionState = cameraPositionState
            ) {

                Marker(
                    state = MarkerState(position = singapore),
                    title = detailsRecipeViewModel.recipe.value?.name,
                    snippet = detailsRecipeViewModel.recipe.value?.description + "\nsdsds",
                )
            }
        }
    }
}