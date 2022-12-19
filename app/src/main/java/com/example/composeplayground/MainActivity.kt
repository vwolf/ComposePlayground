package com.example.composeplayground

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           ComposePlaygroundTheme {
               NavGraph(applicationContext)
           }
        }
    }
}




@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposePlaygroundTheme {
//        Scaffold(
//            modifier = Modifier.fillMaxSize(),
//        ) {
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Greeting("Android")
//                }
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    FirstScreenButton()
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    SecondScreenButton()
//                }
//            }
//        }
//    }
//}