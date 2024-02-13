package com.sunkensplashstudios.VRCRoboScout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.sunkensplashstudios.VRCRoboScout.destinations.EventInformationViewDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun EventView(navController: NavController, event: Event) {

    var event_ by remember { mutableStateOf(event) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        loading = true
        CoroutineScope(Dispatchers.Default).launch {
            event.fetchTeams()
            withContext(Dispatchers.Main) {
                event_ = event
                loading = false
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(event_.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            if (loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(50.dp).padding(10.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
            else {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "EVENT",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                    Card(modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    navController.navigate(
                                        EventInformationViewDestination(event_)
                                    )
                                }
                            ) {
                                Text("Information")
                                Spacer(modifier = Modifier.weight(1.0f))
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    modifier = Modifier.size(18.dp),
                                    contentDescription = "Show Events"
                                )
                            }
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    /*navController.navigate(
                                    EventTeamsViewDestination(event)
                                )*/
                                }
                            ) {
                                Text("Teams")
                                Spacer(modifier = Modifier.weight(1.0f))
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    modifier = Modifier.size(18.dp),
                                    contentDescription = "Show Event Teams"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "SKILLS",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.Gray,
                        fontSize = 13.sp,
                    )
                    Card(modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    /*navController.navigate(
                                    EventSkillsRankingsViewDestination(event)
                                )*/
                                }
                            ) {
                                Text("Skills Rankings")
                                Spacer(modifier = Modifier.weight(1.0f))
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    modifier = Modifier.size(18.dp),
                                    contentDescription = "Show Skills Rankings"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        "DIVISIONS",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color.Gray,
                        fontSize = 13.sp,
                    )
                    Card(modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(10.dp)
                        ) {
                            event_.divisions.forEach { division ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable {
                                        /*navController.navigate(
                                    EventDivisionViewDestination(event)
                                    )*/
                                    }
                                ) {
                                    Text(division.name)
                                    Spacer(modifier = Modifier.weight(1.0f))
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowForwardIos,
                                        modifier = Modifier.size(18.dp),
                                        contentDescription = "Show Division"
                                    )
                                }
                                if (event_.divisions.indexOf(division) != event_.divisions.size - 1) {
                                    HorizontalDivider(
                                        thickness = 1.dp,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}