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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.models.NavigationItem
import com.tagcommander.tcdemo.models.TCModel

@Composable
fun PaymentScreen(navController: NavController)
{
    var fullName by remember{ mutableStateOf("")}
    var cardNumber by remember{ mutableStateOf("4899 4883 4889 8899")}
    var cvc by remember{ mutableStateOf("")}
    var cardExpiration by remember{ mutableStateOf("")}
    val isValidCard = cardNumber.isNotEmpty()

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
        .padding(top = 120.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Information de carte :", fontSize = 18.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        OutlinedTextField(maxLines = 1, value = fullName, onValueChange = {fullName = it}, placeholder = {Text(text = "Nom et Prénom", color = Color.LightGray)}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
        OutlinedTextField(maxLines = 1, value = cardNumber, onValueChange = {cardNumber = it}, placeholder = {Text(text = "Numero de carte", color = Color.LightGray)}, modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(), keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(maxLines = 1, value = cardExpiration, onValueChange = {if (it.length <= 7) cardExpiration = it}, placeholder = {Text(text = "MM/YYYY", color = Color.LightGray)}, modifier = Modifier
                .width(125.dp)
                .padding(8.dp), keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))

            OutlinedTextField(maxLines = 1, value = cvc, onValueChange = {cvc = it}, placeholder = {Text(text = "CVC", color = Color.LightGray)}, modifier = Modifier
                .padding(8.dp)
                .width(80.dp), keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        }

        Button( enabled = isValidCard,
                onClick = { navController.navigateTo(NavigationItem.ShipmentScreen.route) },
                modifier = Modifier.padding(top = 18.dp)) {
            Text(text = "Confirmer le paiement")
        }
    }
}

@Preview
@Composable
fun PayementScreenPreview()
{
    PaymentScreen(rememberNavController())
}