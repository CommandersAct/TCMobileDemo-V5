package com.tagcommander.tcdemo.composables.screens

import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.lib.core.TCSharedPreferences
import com.tagcommander.tcdemo.BottomNavigationBar
import com.tagcommander.tcdemo.R
import com.tagcommander.tcdemo.TCExample.TCConsentImplementation
import org.json.JSONException
import org.json.JSONObject


@Composable
fun JsonVisualizer(navController: NavHostController) {

    val context = LocalContext.current
    var jsonInputText by remember { mutableStateOf("") }
    var selectedLanguage by remember { mutableStateOf("en") }
    var availablelanguages by remember { mutableStateOf(evaluateLanguages(context)) }
/*
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {

        Column (Modifier.padding(it)){

            VisualizerUI(
                modifier = Modifier.weight(8f),
                jsonInputText,
                { v -> jsonInputText = v},
                {jsonInputText = ""},
                { onPastClick(context = context) { v:String -> jsonInputText = v } },
                {
                    applyJson(jsonInputText, context) { langs ->
                        availablelanguages = langs
                        selectedLanguage = availablelanguages.get(0)
                        TCConsent.getInstance().setLanguage(null)
                        TCConsentImplementation.initTCConsent(context, selectedLanguage)
                    }
                }
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Select language :", modifier = Modifier.padding(4.dp))

                TCDropdownMenu(availablelanguages, selectedLanguage) { languageCode ->
                    selectedLanguage = languageCode
                    TCConsent.getInstance().setLanguage(null)
                    TCConsentImplementation.initTCConsent(context, languageCode)
                }

                Button(
                    onClick = { TCConsentImplementation.showPrivacyCenter(context) },
                    Modifier
                        .wrapContentWidth()
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(R.drawable.commandersact_icon),
                            "",
                            Modifier
                                .size(18.dp)
                                .padding(end = 4.dp)
                        )

                        Text(text = "Show Privacy Center", fontSize = 18.sp)
                    }
                }
            }
        }
    }
    */
}

fun onPastClick(context: Context, pastString: (String) -> Unit)
{
    val clipboard:ClipboardManager? = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?

    if (clipboard != null && clipboard.hasPrimaryClip()) {
        val clipboardValue: CharSequence? = clipboard.primaryClip?.getItemAt(0)?.text
        clipboardValue?.let {
            pastString(it.toString())
        }
    }
}

@Composable
fun VisualizerUI(
    modifier: Modifier,
    jsonInputText: String,
    onJsonInputTextChange: (String) -> Unit,
    onResetClick: () -> Unit,
    onPasteClick: () -> Unit,
    onWarningsChanged: () -> Unit
) {
    Column (modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
        OutlinedTextField(
            value = jsonInputText,
            onValueChange = onJsonInputTextChange,
            label = { Text("Privacy.json") },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            maxLines = 7,
        )

        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){

            Button(
                onClick = onWarningsChanged,
                Modifier
                    .height(IntrinsicSize.Max)
                    .padding(start = 2.dp, end = 1.dp)
                    .weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource(R.drawable.commandersact_icon),
                        "",
                        Modifier
                            .size(18.dp)
                            .padding(end = 4.dp)
                    )

                    Text(text = "Apply json", fontSize = 12.sp, color = Color.White)
                }
            }


            Button(
                onClick = onResetClick,
                Modifier
                    .height(IntrinsicSize.Max)
                    .padding(start = 1.dp, end = 1.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.background(Color.Red)
                ) {
                    Image(
                        painterResource(R.drawable.commandersact_icon),
                        "",
                        Modifier
                            .size(18.dp)
                            .padding(end = 4.dp)
                    )

                    Text(text = "Reset", fontSize = 18.sp, color = Color.White)
                }
            }

            Button(
                onClick = onPasteClick,
                Modifier.height(IntrinsicSize.Max)
                    .padding(start = 1.dp, end = 3.dp)
                    .weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painterResource(R.drawable.commandersact_icon),
                        "",
                        Modifier
                            .size(18.dp)
                            .padding(end = 4.dp)
                    )

                    Text(text = "Paste", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}

fun applyJson(jsonInputText: String, context: Context, refreshDropDown: (List<String>) -> Unit) {

    try {
        val privacyJSON = JSONObject(jsonInputText)
        TCSharedPreferences.removeFromSharedPreferences("configuration_privacy", false, context)
        TCSharedPreferences.saveInfoToSharedPreferences(
            "configuration_privacy",
            jsonInputText,
            context
        )
        val availablelanguages = evaluateLanguages(context = context)
        refreshDropDown(availablelanguages)
    } catch (e: Exception) {
         "Invalid JSON !\n" + e.message
    }
}

fun evaluateLanguages(context: Context): List<String> {
    val languagesCodes = listOf(
        "en",
        "ar",
        "bg",
        "bs",
        "ca",
        "cs",
        "da",
        "de",
        "el",
        "es",
        "et",
        "eu",
        "fi",
        "fr",
        "gl",
        "hr",
        "hu",
        "it",
        "ja",
        "lt",
        "lv",
        "mt",
        "nl",
        "no",
        "pl",
        "pt-br",
        "pt-pt",
        "ro",
        "ru",
        "sk",
        "sl",
        "sr-cyrl",
        "sr-latn",
        "sv",
        "tr",
        "zh"
    )

    try {
        val rawPrivacyJson = JSONObject(
            TCSharedPreferences.retrieveInfoFromSharedPreferences(
                "configuration_privacy", context
            )
        )

        return languagesCodes.filter { languageCode -> rawPrivacyJson.has("texts_${languageCode}") }
            .plus("en")
    } catch (e: JSONException) {}

    return languagesCodes
}

@Preview()//device = Devices.AUTOMOTIVE_1024p)
@Composable
fun BannerPreview() {
    JsonVisualizer(rememberNavController())
}

@Composable
fun TCDropdownMenu(
    list: List<String>, languageCode: String, onLanguageSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.background(Color(0xFFE1C8F7))) {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = languageCode, modifier = Modifier.clickable {
                expanded = true
            })

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
            ) {

                for (element in list) {
                    DropdownMenuItem(onClick = {
                        onLanguageSelected(element)
                        expanded = false
                    }) {
                        Text(text = element)
                    }
                }
            }
        }
    }
}