package com.blitz

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blitz.api.NetworkSecurity
import com.google.android.gms.common.GoogleApiAvailability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModalNavigationDrawerSample()
        }
    }

    @Composable
    fun ListColumn() {
        LazyColumn {
            items(200) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    Text(
                        text = "$it",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .wrapContentSize()
                    )
                    Text(
                        text = "Saint-Michel avec un nom très long pour tester le return",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                    )
                }
            }
        }
    }

    private fun isGooglePlayServicesAvailable(context: Context): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)

        return true
        //return resultCode == ConnectionResult.SUCCESS
    }

    @Composable
    @Preview
    fun ModalNavigationDrawerSample() {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(Modifier.height(12.dp))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Lignes de bus") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() } },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                        label = { Text("Favoris") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() } },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        label = { Text("Paramètres") },
                        selected = false,
                        onClick = { scope.launch { drawerState.close() } },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text("Click to open")
                }
                Spacer(modifier = Modifier.weight(1f))

                if (isGooglePlayServicesAvailable(LocalContext.current)) {
                    GoogleMapBox()
                    ApiStmExample()
                    ResizableBox()
                } else {
                    NormalBox()
                }
            }
        }
    }

    @Composable
    fun GoogleMapBox() {

        Text(
            text = ""
        )
    }



    @Composable
    fun ApiStmExample() {/*
        var apiResponse by remember { mutableStateOf("") }

        // Use the HttpClient from OkHttp to make a simple GET request
        val client = NetworkSecurity().createOkHttpClient()
        val request = Request.Builder()
            .url("https://10.0.2.2:7224/horaires/69/55222")
            .build()

        LaunchedEffect(key1 = true) {
            val response = withContext(Dispatchers.IO) {
                client.newCall(request).execute()
            }
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    apiResponse = it
                }
            } else {
                // Handle the error, e.g., show an error message
            }
        }

        Text(
            text = apiResponse
        )*/
    }

    @Composable
    fun ResizableBox() {
        var boxHeight by remember { mutableStateOf(300.dp) }
        var isResizing by remember { mutableStateOf(false) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 8.dp,
                        topStart = 8.dp,
                        bottomEnd = 10.dp,
                        bottomStart = 10.dp
                    )
                )
                .draggable(
                    orientation = Orientation.Vertical,
                    onDragStopped = { isResizing = false },
                    onDragStarted = { isResizing = true },
                    state = rememberDraggableState { delta ->
                        if (isResizing) {
                            boxHeight = (boxHeight - delta.dp * 0.285f).coerceIn(0.dp, 530.dp)
                        }
                    }
                )
        ) {
            Icon(Icons.Default.Menu, contentDescription = null)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight)
        ) {
            ListColumn() // A list of things
        }
    }

    @Composable
    fun NormalBox() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ListColumn() // A list of things
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomDrawer() {

        val modalBottomSheetState = rememberModalBottomSheetState()

        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = modalBottomSheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() },
        ) {
            ListColumn()
        }
    }
}