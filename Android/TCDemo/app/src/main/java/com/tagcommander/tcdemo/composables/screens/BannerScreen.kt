package com.tagcommander.tcdemo

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.TCExample.TCConsentImplementation

@Composable
fun Banner(navController: NavHostController) {

    Scaffold(bottomBar = {BottomNavigationBar(navController)})
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column (modifier = Modifier
                .background(colorResource(id = R.color.colorPrimary))
                .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row() {

                    Button(onClick = { TCConsentImplementation.refuseAllConsent() }, Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painterResource(R.drawable.commandersact_icon), "",
                                Modifier
                                    .size(18.dp)
                                    .padding(end = 4.dp))

                            Text(text = "Refuse All", fontSize = 18.sp)
                        }
                    }

                    Button(onClick = {TCConsentImplementation.acceptAllConsent() }, Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painterResource(R.drawable.commandersact_icon), "",
                                Modifier
                                    .size(18.dp)
                                    .padding(end = 4.dp))

                            Text(text = "Accept All", fontSize = 18.sp)
                        }
                    }
                }

                val context = LocalContext.current
                Button(onClick = { ShowPrivacyCenter(context) }, Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.commandersact_icon), "",
                            Modifier
                                .size(18.dp)
                                .padding(end = 4.dp))

                        Text(text = "Show Privacy Center", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

fun ShowPrivacyCenter(context: Context) {
    TCConsentImplementation.showPrivacyCenter(context)
}

@Preview
@Composable
fun BannerPreview() {
    Banner(rememberNavController())
}
