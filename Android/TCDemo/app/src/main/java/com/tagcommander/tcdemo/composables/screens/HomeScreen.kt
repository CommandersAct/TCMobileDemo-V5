package com.tagcommander.tcdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.models.TCModel
@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Scaffold(bottomBar = {BottomNavigationBar(navController)})
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column (modifier = Modifier
                .verticalScroll(scrollState)
                .background(colorResource(id = R.color.colorPrimary))
                .fillMaxSize()
                .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Spacer(modifier = Modifier.height(80.dp))
                Text(text = stringResource(id = R.string.welcome_to_app_text),
                    color = colorResource(id = R.color.text_colorPrimary),
                    fontSize = 34.sp,
                    textAlign = TextAlign.Center)

                Image(painter = painterResource(id = R.drawable.commanders_banner), contentDescription = "")

                val result = TCModel.products.groupBy { it.category }

                for ((k, v) in result) {
                    ProductRow(products = v, title = k, navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    TCModel.loadProducts(LocalContext.current)
    HomeScreen(rememberNavController())
}
