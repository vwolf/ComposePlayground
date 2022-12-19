package com.example.composeplayground

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import com.example.composeplayground.secondScreen.BottomSheet
import com.example.composeplayground.secondScreen.SecondScreenMapView
import com.example.composeplayground.secondScreen.SecondScreenViewModel
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SecondScreen(
    navAction: AppNavigationActions,
    viewModel: SecondScreenViewModel = SecondScreenViewModel()
) {
    println("SecondScreen")

    var scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val mapViewLayout: MapView = SecondScreenMapView()
    val showModalSheet = rememberSaveable { mutableStateOf( false ) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            println( "rememberModalBottomSheetState.confirmStateChange $it")
            if (it == ModalBottomSheetValue.Hidden) {
                showModalSheet.value = false
            }
            true
        }
    )

    LocalLifecycleOwner.current.lifecycleScope.launch {
        viewModel.sharedUiState.collect {
            showModalSheet.value = it.bottomSheetState
        }
    }


    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheet() }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBarSecond()}
        ) {
            MapViewComposable(
                context = context,
                mapViewLayout = mapViewLayout)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "SecondScreen")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                )  {
                    Button(
                        onClick = {
                            viewModel.setBottomSheetState( !showModalSheet.value )
                            // showModalSheet.value = !showModalSheet.value
                            }
                    ) {
                        Text(text = "Open Bottomsheet")
                    }
                }
            }
            ModalBottomSheetWithAnchor(sheetState = sheetState, showModalSheet = showModalSheet)
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetWithAnchor(
    sheetState: ModalBottomSheetState,
    showModalSheet: MutableState<Boolean>
) {
    println("ModalBottomSheetWithAnchor showModalSheet: ${showModalSheet.value}")
    val scope = rememberCoroutineScope()
    scope.launch {
        if (sheetState.isVisible) {
            sheetState.hide()
        } else {
            if (showModalSheet.value) {
                sheetState.show()
            }
        }
    }
}


@Composable
fun MapViewComposable(
    context: Context,
    mapViewLayout: MapView
) {
    AndroidView(
        { mapViewLayout },
        modifier = Modifier
    ) { mapView ->
        println("SeccondScreen.MapViewComposable")
        org.osmdroid.config.Configuration.getInstance().userAgentValue = context.packageName
        mapView.useDataConnection()
        mapView.controller.setCenter(GeoPoint(52.4908, 13.4186))
        mapView.controller.setZoom(11.0)

        mapView.setMultiTouchControls(true)
        val rotationGestureOverlay = RotationGestureOverlay(mapView)
        rotationGestureOverlay.isEnabled = true
        mapView.overlays.add(rotationGestureOverlay)

    }
}


// onLoad: ((map: MapView) -> Unit)? = null,


/********************************** FeatureBar ****************************************************/

/**
 * The FeatureBar provide ui for
 * FeatureBar is a Row with ui elements
 *
 * material icons Location On, Location Off, Photo Camera, VideoCam, Mic, Add Circle, Add A Photo
 *
 * @param onClick event handler consumed in MapViewModel.featureBarEvent()
 */
@Composable
fun FeatureBar( onClick: (BottomSheetTypes) -> Unit) {
    Row(
        modifier = Modifier
            .background(Color.DarkGray.copy(alpha = 0.5f))
            .fillMaxWidth()
            .height(64.dp)
            .padding(8.dp, 8.dp)
    ) {
        FeatureBarIconButton(
            onClick = onClick ,
            id = BottomSheetTypes.WAYPOINT,
            icon = Icons.Filled.AddCircle)
        FeatureBarIconButton(onClick = onClick, id = BottomSheetTypes.CAMERA, icon = Icons.Filled.PhotoCamera)
        FeatureBarIconButton(onClick = onClick, id = BottomSheetTypes.VIDEO, icon = Icons.Filled.Videocam)
        FeatureBarIconButton(onClick = onClick, id = BottomSheetTypes.AUDIO, icon = Icon(R.drawable.outline_mic_24, null))
        //LocationButton( onGoTo = {} )
    }
}


@Composable
fun FeatureBarIconButton(
    modifier: Modifier = Modifier,
    id: BottomSheetTypes = BottomSheetTypes.WAYPOINT,
    onClick: (BottomSheetTypes) -> Unit,
    icon: ImageVector,
) {
    val currentLocalContext = LocalContext.current
    val scope = rememberCoroutineScope()
    val showModalSheet = remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            println("click")
            onClick(id)
        }
    ) {
        Icon(icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(64.dp)
        )
        //Icon(Icons.Filled.Menu, contentDescription = "More Menu")
    }
}