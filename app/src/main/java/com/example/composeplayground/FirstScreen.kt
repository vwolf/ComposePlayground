package com.example.composeplayground

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme


@Composable
fun FirstScreen(
    navAction: AppNavigationActions
) {

    Scaffold (
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp, 8.dp),
        content = {
            Modifier.padding(it)
            Text(text = "First Screen")

            Column (
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BackButton(navAction = navAction)
                }
            }
        }
    )
}
