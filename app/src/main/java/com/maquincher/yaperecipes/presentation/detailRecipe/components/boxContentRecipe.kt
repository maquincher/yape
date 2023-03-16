package com.maquincher.yaperecipes.presentation.detailRecipe.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maquincher.yaperecipes.R
import com.maquincher.yaperecipes.models.Recipe

@Composable
fun boxContentRecipe(recipe: Recipe) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Column {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                text = recipe.description,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                )

            )

            Text(
                text = stringResource(id = R.string.ingredients),
                modifier = Modifier.fillMaxWidth(),

                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            recipe.ingredients.forEach {
                Text(text = it)
            }
        }
    }
}