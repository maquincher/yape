package com.maquincher.yaperecipes.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.maquincher.yaperecipes.navigation.AppNavigation
import com.maquincher.yaperecipes.ui.theme.YapeRecipesTheme
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MyComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @RelaxedMockK
    @Inject
    lateinit var nav: AppNavigation

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun validateInitialization() {
        // Start the app
        composeTestRule.setContent {
            YapeRecipesTheme {
                nav.create()
            }
        }
        composeTestRule.onNodeWithText("Bienvenido")
    }
}
