package com.maquincher.yaperecipes.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maquincher.yaperecipes.presentation.home.HomeViewModel

@Composable
fun searchBar(
    valueSearched: String,
    homeViewModel: HomeViewModel,
    onSearchClick: (String) -> Unit
) {
    TextField(
        value = valueSearched,
        onValueChange = { homeViewModel.onFilterChanged(it) },
        leadingIcon = {
            IconButton(onClick = { onSearchClick(valueSearched) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )

            }

        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text("Ingrese Nombre de receta a buscar")
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}