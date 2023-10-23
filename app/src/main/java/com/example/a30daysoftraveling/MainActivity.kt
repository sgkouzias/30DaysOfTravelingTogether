package com.example.a30daysoftraveling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysoftraveling.model.Day
import com.example.a30daysoftraveling.model.DaysData
import com.example.a30daysoftraveling.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DaysApp()
                }
            }
        }
    }
}

/**
 * Main application function.Utilizes the TopAppBar and DisplayDays functions.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaysApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ) {
        val days = DaysData.days
        DisplayDays(days = days, contentPadding = it)
    }
}

/**
 * Utility function to display top app bar.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "30 Days Of Traveling Together",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier.padding(8.dp)
    )
}

/**
 * Utility function to display the days. Utilizes the DayItem function.
 * @param days - A list of Day class instances from the model package.
 */

@Composable
fun DisplayDays(
    days: List<Day>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(days) {
            DayItem(
                day = it,
                modifier = Modifier.padding(8.dp))
        }
    }
}

/**
 * Utility function to display the a Day class instance.
 * @param Day -> Day class from the model package.
 */


@Composable
fun DayItem(
    day: Day,
    modifier: Modifier = Modifier
    ) {

    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
       Column(horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.padding(16.dp)
       ) {
           Text(
               text = day.indicator,
               style = MaterialTheme.typography.labelSmall
           )
           Text(
               text = day.title,
               style = MaterialTheme.typography.titleLarge,
               textAlign = TextAlign.Justify,
               modifier  = Modifier
                   .padding(8.dp)
                   .wrapContentWidth()
           )
           Image(
               painter = painterResource(id = day.image),
               contentDescription = day.title,
               contentScale = ContentScale.FillWidth,
               modifier = Modifier
                   .height(150.dp)
                   .clickable { expanded = !expanded }
           )
            if(expanded) {
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                   text = day.idea,
                   style = MaterialTheme.typography.bodyMedium,
                   textAlign = TextAlign.Justify
               )
           }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun DaysOfTravelingPreview() {
    AppTheme {
        DaysApp()
    }
}