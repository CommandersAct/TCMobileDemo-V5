package com.tagcommander.tcdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.composables.screens.MainScreen
import com.tagcommander.tcdemo.models.NavigationItem
import com.tagcommander.tcdemo.models.TCModel
import com.tagcommander.tcdemo.ui.theme.TCDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TCModel.loadProducts(this.applicationContext)
        setContent {
            MaterialTheme() {
                MainScreen()
            }
        }
    }
}