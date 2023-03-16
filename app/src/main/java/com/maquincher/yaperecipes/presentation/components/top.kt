package com.maquincher.yaperecipes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun top(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {

    Column(
        modifier = Modifier
            .padding(0.dp)
            //.absoluteOffset(y=0.dp, x = 0.dp)
            .fillMaxWidth()
    ) {

        TopAppBar(
            title = {

                Text(
                    text = "Recetas",
                    color = Color.White
                )/*Icon(imageVector = Icons.Default.CheckCircle,contentDescription = "Abrir menú") */
            },
            backgroundColor = Color(
                red = 86,
                green = 109,
                blue = 221
            ),//Color(R.color.principal),//Color(stringResource(id = R.color.principal)/*red = 86, green = 109, blue = 221*/),
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Abrir menú",
                        tint = Color.White
                    )
                }
            },
            actions = {

                IconButton(onClick = { /*TODO*/ }) {


                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )

                }


                IconButton(onClick = { /*TODO*/ }) {


                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Perfil",
                        tint = Color.White
                    )

                }
            }
        )

    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun defaultTop() {
    val navController = rememberNavController()
    top(navController,"test")

}*/