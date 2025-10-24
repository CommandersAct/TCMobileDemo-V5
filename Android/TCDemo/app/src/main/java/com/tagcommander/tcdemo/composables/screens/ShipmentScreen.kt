package com.tagcommander.tcdemo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.TCExample.TCServerSideImplementation
import com.tagcommander.tcdemo.models.NavigationItem

@Composable
fun ShipmentScreen(navController: NavHostController)
{
    var adress by remember{ mutableStateOf("3b rue taylor")}
    var adressComplement by remember{ mutableStateOf("")}
    var city by remember{ mutableStateOf("")}
    var zipCode by remember{ mutableStateOf("75010")}
    val isValidAdress = adress.isNotEmpty() && zipCode.isNotEmpty()

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
        .padding(top = 120.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Information de livraison :", fontSize = 18.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        OutlinedTextField(maxLines = 1, value = adress, onValueChange = {adress = it}, placeholder = {Text(text = "Adresse", color = Color.LightGray)}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
        OutlinedTextField(maxLines = 1, value = adressComplement, onValueChange = {adressComplement = it}, placeholder = {Text(text = "Complément", color = Color.LightGray)}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
        OutlinedTextField(maxLines = 1, value = city, onValueChange = {city = it}, placeholder = {Text(text = "Ville", color = Color.LightGray)}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
        OutlinedTextField(maxLines = 1, value = zipCode, onValueChange = {if (it.length <= 5) zipCode = it}, placeholder = {Text(text = "Code postale", color = Color.LightGray)}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(), keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))


        Button( enabled = isValidAdress,
                onClick = { TCServerSideImplementation.sendAddShippingInfo("$adress $adressComplement $city $zipCode")
                            navController.navigateTo(NavigationItem.OrderRecapScreen.route) },
                modifier = Modifier.padding(top = 18.dp)) {
            Text(text = "Confirmer le paiement")
        }
    }
}

@Preview
@Composable
fun ShipmentScreenPreview()
{
    ShipmentScreen(rememberNavController())
}