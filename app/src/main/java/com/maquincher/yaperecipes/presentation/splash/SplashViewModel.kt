package com.maquincher.yaperecipes.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.maquincher.yaperecipes.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {

    private val _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean> = _finished

    suspend fun nextPage(navController: NavController) {
        _finished.value = true
        delay(500L)
        navController.navigate(route = AppScreens.homeScreen.route)
    }


    suspend fun initAnimation(scaleAnimation: Animatable<Float, AnimationVector1D>, durationMillisAnimation: Int,)
    {
        _finished.value = true
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )
    }
}