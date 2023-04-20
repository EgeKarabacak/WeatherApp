package com.example.weatherapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R.drawable.airquality

// Data model
data class WeatherCard(
    val time: String,
    val message: String,
    val temp: Double,
)


// Single card
@Composable
fun WeatherUpdateCard(weatherCard: WeatherCard) {
    Box {
        val colorStops = arrayOf(
            0.0f to Color(131,58,180),
            0.7f to Color(253,29,29),
            1f to Color(252,176,69)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .background(
                    brush = Brush.horizontalGradient(colorStops = colorStops),
                )

        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = weatherCard.time, // "2 minutes ago",
                    style = MaterialTheme.typography.caption

                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = weatherCard.message, // "If you don't want to get wet today, don't forget your umbrella.",
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Image(painter = painterResource(id = airquality), contentDescription = "Air Quality",
            Modifier
                .size(60.dp)
                .align(alignment = Alignment.TopEnd)
                .offset(x = (-40).dp, y= (20).dp)
                .clip(shape = RoundedCornerShape(20.dp))
        )
    }
}