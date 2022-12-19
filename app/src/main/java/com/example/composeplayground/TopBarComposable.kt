package com.example.composeplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    navAction: AppNavigationActions,
    title: String = "ComposePlayground"
) {

    TopAppBar(
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Settings, null)
            }
        },
        title = { Text(title) },
        backgroundColor = MaterialTheme.colors.primarySurface
    )
}

@Composable
fun TopBarSecond(
    title: String = "ComposePlaygroundSecond"
) {
    TopAppBar (
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Text(text = title)
        IconButton(onClick = { /*TODO*/ }
        ) {
            Icon(Icons.Filled.Settings, null)
        }

    }
}