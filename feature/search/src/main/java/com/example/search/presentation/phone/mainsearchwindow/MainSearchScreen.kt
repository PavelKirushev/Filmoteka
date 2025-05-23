package com.example.search.presentation.phone.mainsearchwindow

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.search.R
import com.example.search.presentation.phone.FilmCard

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainSearchScreen(viewModel: MainSearchViewModel, controller: NavHostController) {
    val searchState by viewModel.search.collectAsState()

    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Icon(
                androidx.compose.ui.res.painterResource(R.drawable.search_icon),
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            SimpleTextField(
                onValueChange = { newText ->
                    viewModel.setKeyword(newText)
                },
                keyboardActions = KeyboardActions(onDone = { viewModel.loadSearch(1) }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            )
        }
        searchState?.films?.let { films ->
            LazyColumn {
                items(films) { film ->
                    FilmCard(film = film, controller = controller)
                    HorizontalDivider(color = MaterialTheme.colorScheme.primary, thickness = 0.5.dp)
                }
            }
        }
    }
}