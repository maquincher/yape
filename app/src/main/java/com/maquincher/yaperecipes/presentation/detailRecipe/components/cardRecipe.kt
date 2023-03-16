package com.maquincher.yaperecipes.presentation.detailRecipe.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maquincher.yaperecipes.models.Recipe

@Composable
fun cardRecipe(recipe: Recipe) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item { boxImageRecipe(recipe = recipe) }

            item { boxContentRecipe(recipe = recipe) }
        }
    }
}