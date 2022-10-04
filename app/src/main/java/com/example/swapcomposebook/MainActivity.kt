package com.example.swapcomposebook

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.swapcomposebook.ui.theme.SwapComposeBookTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapComposeBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(topBar = { TopBar() }) {
                        RadioButtonsDemo()
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonsDemo() {
    var radioOptions = listOf<String>("Male", "Female", "Others")
    var selected by remember {
        mutableStateOf("")
    }
    RadioButtons(radioOptions, selected, onRadioButtonChange = {
        selected = it
    })
}

@Composable
fun RadioButtons(
    radioOptions: List<String>,
    selected: String,
    onRadioButtonChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.wrapContentSize(Alignment.TopCenter),
        verticalAlignment = Alignment.CenterVertically
    ) {
        radioOptions.forEach { text ->
            RadioButton(
                selected = selected == text,
                onClick = { onRadioButtonChange(text) },
                colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primaryVariant)
            )
            Text(text = text, modifier = Modifier
                .clickable {
                    onRadioButtonChange(text)
                })
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(navigationIcon = {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_book_24),
                contentDescription = "ic_book"
            )
        }
    }, title = { Text(text = "SwapComposeBook") })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SwapComposeBookTheme {
        RadioButtonsDemo()
    }
}