package com.mmartinsvoboda.sporttrackingapp.presentation.components.map

import android.location.Address
import androidx.compose.runtime.Composable
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
    onProblem: @Composable () -> Unit
) {
    val context = LocalContext.current

    val addressState = MapHelper.getAddressObjectFlow(
        address,
        context
    ).collectAsState(null)
    val cameraPositionState = rememberCameraPositionState()

    MapViewContainer(
        address = addressState.value,
        title = title,
        cameraPositionState = cameraPositionState,
        onProblem = onProblem
    )
}

@Composable
private fun MapViewContainer(
    address: Address?,
    title: String,
    cameraPositionState: CameraPositionState,
    onProblem: @Composable () -> Unit
) {
    if (address != null) {
        cameraPositionState.position =
            CameraPosition.fromLatLngZoom(
                LatLng(
                    address.latitude,
                    address.longitude
                ),
                13f
            )

        GoogleMap(
            modifier = Modifier,
            cameraPositionState = cameraPositionState,
            properties = getMapProperties(),
            uiSettings = getUiSettings()
        ) {
            val destination = LatLng(
                address.latitude,
                address.longitude
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