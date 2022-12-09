package com.tagcommander.tcdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tagcommander.tcdemo.models.NavigationItem
import com.tagcommander.tcdemo.models.TCModel

@Composable
fun OrderValidationScreen(navController: NavHostController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success))
    val progress by animateLottieCompositionAsState(composition)
    var finished = remember{mutableStateOf(false)}

    Box( Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        LottieAnimation(composition = composition, progress = { progress }, modifier = Modifier.padding(120.dp))
    }

    if (progress == 1f && !finished.value) {
        navController.navigateUp()
        TCExample.sendPageViewEvent(NavigationItem.Home.title, "view_page")
        TCModel.resetAll(LocalContext.current)
        finished.value = true
    }
}

@Preview
@Composable
fun OrderValidationScreenPreview() {
    OrderValidationScreen(rememberNavController())
}