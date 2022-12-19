package com.example.composeplayground

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navAction: AppNavigationActions,
    navController: NavController
) {

    ComposePlaygroundTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(navAction = navAction)}
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Greeting("Android")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FirstScreenButton(navAction)
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SecondScreenButton(navAction)
                }
            }
        }
    }
}

@Composable
fun FirstScreenButton(navAction: AppNavigationActions) {
    Button(onClick = { navAction.navigateToFirstScreen()},
        modifier = Modifier.height(32.dp)
    ) {
        Text(text = "first screen")
    }
}

@Composable
fun SecondScreenButton(navAction: AppNavigationActions) {
    Button(
        onClick = { navAction.navigateToSecondScreen()},
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text = "Second Screen")
    }
}

@Composable
fun BackButton(navAction: AppNavigationActions) {
    Button(
        onClick = { navAction.navigateToMain() },
        shape = MaterialTheme.shapes.large
    ) {
        Text(text = "Back")
    }
}