package com.mmartinsvoboda.sporttrackingapp.presentation.components.map

import android.location.Address
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.mmartinsvoboda.sporttrackingapp.R
import com.mmartinsvoboda.sporttrackingapp.presentation.components.lightModeAndDarkModeValues

@Composable
fun Map(
    address: String,
    title: String,
    modifier: Modifier = Modifier,
    onProblem: @Composable () -> Unit
) {
    val context = LocalContext.current

    val addressState = MapHelper.getAddressObjectFlow(
        address,
        context
    ).collectAsState(null)
    val cameraPositionState = rememberCameraPositionState()

    MapViewContainer(
        address = addressState,
        title = title,
        cameraPositionState = cameraPositionState,
        onProblem = onProblem,
        modifier = modifier
    )
}

@Composable
private fun MapViewContainer(
    address: State<Address?>,
    title: String,
    cameraPositionState: CameraPositionState,
    modifier: Modifier = Modifier,
    onProblem: @Composable () -> Unit
) {
    if (address.value != null) {
        cameraPositionState.position =
            CameraPosition.fromLatLngZoom(
                LatLng(
                    address.value!!.latitude,
                    address.value!!.longitude
                ),
                13f
            )

        GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState,
            properties = getMapProperties(),
            uiSettings = getUiSettings()
        ) {
            val destination = LatLng(
                address.value!!.latitude,
                address.value!!.longitude
            )
            val markerState = rememberMarkerState(
                key = title,
                position = destination
            )

            Marker(
                title = title,
                state = markerState
            )
        }
    } else {
        onProblem()
    }
}

@Composable
fun getMapProperties(): MapProperties {
    val context = LocalContext.current

    val lightAndDarkModeStyles = lightModeAndDarkModeValues(
        lightMode = R.raw.light_map_style,
        darkMode = R.raw.dark_map_style
    )

    return MapProperties(
        isBuildingEnabled = false,
        isIndoorEnabled = false,
        isMyLocationEnabled = false,
        isTrafficEnabled = false,
        mapType = MapType.NORMAL,
        mapStyleOptions = MapStyleOptions.loadRawResourceStyle(
            context,
            lightAndDarkModeStyles
        )
    )
}

fun getUiSettings(): MapUiSettings {
    return MapUiSettings(
        compassEnabled = true,
        indoorLevelPickerEnabled = false,
        mapToolbarEnabled = true,
        myLocationButtonEnabled = false,
        rotationGesturesEnabled = true,
        scrollGesturesEnabled = true,
        scrollGesturesEnabledDuringRotateOrZoom = false,
        tiltGesturesEnabled = false,
        zoomControlsEnabled = true,
        zoomGesturesEnabled = true
    )
}