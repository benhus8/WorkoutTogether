package com.hudyweas.workouttogether.screens.weather_screen

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel


import kotlinx.coroutines.launch
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint

import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.MinimapOverlay
import org.osmdroid.views.overlay.OverlayItem

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val weatherState = viewModel.weather.value
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.getWeather()
        }
    }
        Text(text ="Weather Screen")
        Text(text = weatherState?.location?.name ?: "Loading...")
//        OsmdroidMapView()
    SmallOsmdroidMapView()
}


@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}

@Composable
fun OsmdroidMapView() {
    val context = LocalContext.current
    val items = remember { mutableStateListOf<OverlayItem>() }
    val mapView = MapView(context)
    mapView.isClickable = true

    // Initial point in London
    items.add(OverlayItem("Title", "Description", GeoPoint(51.5074, -0.1278)))

    var overlay = remember {
        ItemizedOverlayWithFocus<OverlayItem>(items, object :
            ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
            override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
                println("Item $index tapped")
                return true
            }

            override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
                return false
            }
        }, context)
    }
    overlay.setFocusItemsOnTap(true)

    val mapEventsReceiver = object : MapEventsReceiver {
        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
            return false
        }

        override fun longPressHelper(p: GeoPoint?): Boolean {
            p?.let {
                // Add a new point on long press
                val newPoint = OverlayItem("New Point", "Long Press", it)
                items.add(newPoint)
                overlay.setFocusItemsOnTap(true)
                mapView.overlayManager.add(overlay)
                mapView.invalidate()
                println("Long press at $it")
                println(items.size)
                return true
            }
            return false
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
//            val mapView = MapView(context)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setBuiltInZoomControls(true)
            mapView.setMultiTouchControls(true)
            mapView.overlays.add(overlay)
            mapView.overlays.add(MapEventsOverlay(mapEventsReceiver))


            // Set the user agent
            org.osmdroid.config.Configuration.getInstance().userAgentValue = "WorkoutTogether"

            // Set the initial location to London
            val mapController = mapView.controller
            mapController.setZoom(13.0)
            val startPoint = GeoPoint(51.5074, -0.1278) // London's latitude and longitude
            mapController.setCenter(startPoint)
            mapView
        }
    )
}

@Composable
fun SmallOsmdroidMapView() {
    val context = LocalContext.current
    val items = remember { mutableStateListOf<OverlayItem>() }
    val mapView = MapView(context)
    mapView.isClickable = true

    // Initial point in London
    items.add(OverlayItem("Title", "Description", GeoPoint(51.5074, -0.1278)))

    var overlay = remember {
        ItemizedOverlayWithFocus<OverlayItem>(items, object :
            ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
            override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
                println("Item $index tapped")
                return true
            }

            override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
                return false
            }
        }, context)
    }
    overlay.setFocusItemsOnTap(true)

    val mapEventsReceiver = object : MapEventsReceiver {
        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
            return false
        }

        override fun longPressHelper(p: GeoPoint?): Boolean {
            p?.let {
                // Add a new point on long press
                val newPoint = OverlayItem("New Point", "Long Press", it)
                items.add(newPoint)
                overlay.setFocusItemsOnTap(true)
                mapView.overlayManager.add(overlay)
                mapView.invalidate()
                println("Long press at $it")
                println(items.size)
                return true
            }
            return false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(), // Ustawia kontener na całą dostępną przestrzeń
    ) {
        AndroidView(
            modifier = Modifier
                .width(300.dp) // Ustawia szerokość mapy na 300 punktów
                .height(200.dp), // Ustawia wysokość mapy na 200 punktów
            factory = { context ->
                mapView.setTileSource(TileSourceFactory.MAPNIK)
                mapView.setBuiltInZoomControls(true)
                mapView.setMultiTouchControls(true)
                mapView.overlays.add(overlay)
                mapView.overlays.add(MapEventsOverlay(mapEventsReceiver))

                // Set the user agent
                org.osmdroid.config.Configuration.getInstance().userAgentValue = "WorkoutTogether"

                // Set the initial location to London
                val mapController = mapView.controller
                mapController.setZoom(13.0)
                val startPoint = GeoPoint(51.5074, -0.1278) // London's latitude and longitude
                mapController.setCenter(startPoint)
                mapView
            }
        )
    }
}


