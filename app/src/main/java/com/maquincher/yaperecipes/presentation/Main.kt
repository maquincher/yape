package com.maquincher.yaperecipes.presentation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

import com.maquincher.yaperecipes.presentation.components.DrawerContent

import com.maquincher.yaperecipes.presentation.components.top

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class Main @Inject constructor(
    private val drawerContent: DrawerContent
) {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    @Composable
    fun create(
        navController: NavController,
        display: @Composable () -> Unit = {}
    ) {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val coroutineScope = rememberCoroutineScope()
        val contextForToast = LocalContext.current.applicationContext



        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {

                drawerContent.create { item ->
                    Toast
                        .makeText(contextForToast, item.label, Toast.LENGTH_SHORT)
                        .show()
                    when (item.label) {
                        "Logout" -> {

                            navController.navigate(item.destination)
                        }
                        else -> {
                            print("$item")
                        }
                    }

                    coroutineScope.launch {
                        // delay for the ripple effect
                        delay(timeMillis = 250)
                        scaffoldState.drawerState.close()
                    }
                }
            },
        ) {
            top( scaffoldState, scope)
            display()
        }

    }
}