package com.maquincher.yaperecipes.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.maquincher.yaperecipes.models.Recipe
import com.maquincher.yaperecipes.presentation.home.HomeViewModel

@Composable
fun CardRecipe(recipe: Recipe, homeViewModel: HomeViewModel, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
            .clickable {
                homeViewModel.onClickRecipe(recipe, navController)
                //onDetailClick(recipe.id)
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = Modifier.height(110.dp)
        ){
            AsyncImage(
                model = recipe.imgSrcUrl,
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 12.dp))
                    .fillMaxSize()
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 100f
                    )
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomCenter
            ){

                Text(
                    text = recipe.name,
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}
