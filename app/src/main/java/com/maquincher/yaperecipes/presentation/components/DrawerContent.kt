package com.maquincher.yaperecipes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maquincher.yaperecipes.navigation.AppScreens
import javax.inject.Inject

class DrawerContent @Inject constructor() {

    @Composable
    fun create(
        gradientColors: List<Color> = listOf(Color.Gray, Color.Blue),
        itemClick: (NavigationDrawerItem) -> Unit
    ) {

        val itemsList = prepareNavigationDrawerItems()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = gradientColors)),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = 36.dp)
        ) {

            items(itemsList) { item ->
                NavigationListItem(item = item) {
                    if(item.enable)
                        itemClick(item)
                    else
                    {
                        print("Option don't validate $item")
                    }
                }
            }
        }
    }

    @Composable
    private fun NavigationListItem(
        item: NavigationDrawerItem,
        unreadBubbleColor: Color = Color(0xFF0FFF93),
        itemClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    itemClick()
                }
                .padding(horizontal = 24.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box {

                item.image.let{item.image}.let {
                    Icon(
                        modifier = Modifier
                            .padding(all = if (item.showUnreadBubble && item.label == "Messages") 5.dp else 2.dp)
                            .size(size = if (item.showUnreadBubble && item.label == "Messages") 24.dp else 28.dp),
                        imageVector = it,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                if (item.showUnreadBubble) {
                    Box(
                        modifier = Modifier
                            .size(size = 8.dp)
                            .align(alignment = Alignment.TopEnd)
                            .background(color = unreadBubbleColor, shape = CircleShape)
                    )
                }
            }

            // label
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = item.label,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = if(item.enable) Color.White else Color.Gray
            )
        }
    }

    @Composable
    private fun prepareNavigationDrawerItems(): List<NavigationDrawerItem> {
        val itemsList = arrayListOf<NavigationDrawerItem>()

        itemsList.add(
            NavigationDrawerItem(
                image = Icons.Filled.Home,
                label = "Home"
            )
        )


        return itemsList
    }

    data class NavigationDrawerItem(
        val image: ImageVector,
        val label: String,
        val showUnreadBubble: Boolean = false,
        val destination: String =  AppScreens.homeScreen.route,
        val action: @Composable () -> Unit = {},
        val enable: Boolean = true
    )
}