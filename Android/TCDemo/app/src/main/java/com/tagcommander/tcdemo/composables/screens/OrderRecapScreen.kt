package com.tagcommander.tcdemo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.models.NavigationItem


@Composable
fun OrderRecapScreen(navController: NavHostController)
{   
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
            .padding(top = 120.dp)) {

        Text(text = "Adresse de livraison :", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
        Text(text = "3b rue Taylor", fontSize = 18.sp, color = Color.LightGray, modifier = Modifier.padding(8.dp))
        Text(text = "Paris", fontSize = 18.sp, color = Color.LightGray, modifier = Modifier.padding(8.dp))
        Text(text = "75010", fontSize = 18.sp, color = Color.LightGray, modifier = Modifier.padding(8.dp))

        Text(text = "Adresse de livraison :", fontSize = 24.sp, modifier = Modifier.padding(8.dp))
        Text(text = "***** ****** ****** 4889", fontSize = 18.sp, color = Color.LightGray, modifier = Modifier.padding(8.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Montant Total :", fontSize = 24.sp)
            Spacer(Modifier.weight(1f))
            Text(text = "889 Euros", fontSize = 24.sp, color = Color.LightGray)
        }

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
        {
            Button(onClick = { navController.navigateAndPopHome(NavigationItem.OrderValidationScreen.route, NavigationItem.Home.route) }, Modifier.padding(top = 64.dp)) {
                Text(text = "Valider la commande")
            }
        }
    }
}

@Preview
@Composable
fun OrderRecapScreenPreview()
{
    OrderRecapScreen(rememberNavController())
}