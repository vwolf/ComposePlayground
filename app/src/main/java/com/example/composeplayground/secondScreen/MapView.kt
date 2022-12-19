package com.example.composeplayground.secondScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.example.composeplayground.R
import org.osmdroid.views.MapView

@Composable
fun SecondScreenMapView() : MapView {

    println("SecondScreenMapView")

    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply { id = R.id.map }
    }

    // makes MapView follow the lifecyle of this composable
    val lifecycleObserver = mapScreenLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect( lifecycle ) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycle.removeObserver(lifecycleObserver) }
    }

    return mapView
}

@Composable
fun mapScreenLifecycleObserver(mapView: MapView): LifecycleObserver = remember(mapView) {
    LifecycleEventObserver { livecycleOwner, event ->
        println("MapView.mapScreenLifecycleObserver $livecycleOwner with event $event")
        when (event) {
            Lifecycle.Event.ON_RESUME -> mapView.onResume()
            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
            else -> {}
        }
    }
}