package com.maquincher.yaperecipes.presentation.home


import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.maquincher.yaperecipes.R
import com.maquincher.yaperecipes.models.Recipe
import com.maquincher.yaperecipes.presentation.home.components.CardRecipe
import com.maquincher.yaperecipes.presentation.home.components.searchBar
import javax.inject.Inject


class HomePage @Inject constructor(

) {

    @Composable
    fun create(
        navController: NavController,
        modifier: Modifier = Modifier
    ) {
        val homeViewModel = hiltViewModel<HomeViewModel>()
        val filter: String by homeViewModel.filter.observeAsState(initial = "")
        val recipes: List<Recipe> by homeViewModel.recipe.observeAsState(initial = emptyList())

        Column(modifier = Modifier.padding(top = 60.dp, start = 5.dp, end = 5.dp)) {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                searchBar(filter, homeViewModel) {
                    homeViewModel.onSearchClick(filter)
                }
            }


            Row(modifier = modifier.padding(10.dp)) {
                Text(
                    text = stringResource(id = R.string.recipes),
                    modifier = Modifier.fillMaxWidth(),

                    style = TextStyle(
                        color = Color(R.color.principal),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Row(modifier = modifier.padding(10.dp)) {



                LazyVerticalGrid(
                    columns = GridCells.Fixed(
                        when (LocalConfiguration.current.orientation) {
                            Configuration.ORIENTATION_LANDSCAPE -> 4
                            else -> 2
                        }
                    ),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    runCatching {
                        itemsIndexed(recipes) { _, item ->
                            CardRecipe(item, homeViewModel, navController)
                        }
                    }
                }
            }
        }
    }

}



