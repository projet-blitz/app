package com.blitz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

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

    @OptIn(ExperimentalMaterial3Api::class)
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
                //.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                Spacer(Modifier.height(20.dp))
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text("Click to open")
                }
                Spacer(modifier = Modifier.weight(1f))
                ResizableBox()
            }
        }
    }


    @Composable
    fun ResizableBox() {
        var boxHeight by remember { mutableStateOf(300.dp) }
        var isResizing by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.Black)
                .clip(RoundedCornerShape(topEnd = 8.dp , topStart = 8.dp, bottomEnd = 10.dp, bottomStart = 10.dp))
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        isResizing = true
                        boxHeight -= (delta * 0.285f).dp
                        Log.i("", boxHeight.toString())

                        if (boxHeight < 0.dp)
                            boxHeight = 0.dp


                        if (boxHeight > 530.dp)
                            boxHeight = 530.dp

                    }
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight)
        ) {
            ListColumn()
        }
    }
}