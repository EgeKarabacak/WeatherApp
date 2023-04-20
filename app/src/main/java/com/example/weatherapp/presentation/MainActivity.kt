package com.example.weatherapp.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.api.WeatherType
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.presentation.ui.MainViewModel
import com.example.weatherapp.presentation.ui.WeatherCard
import com.example.weatherapp.presentation.ui.WeatherUpdateCard
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val exampleViewModel: MainViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val granted = permissions.values.all { it }
            if (granted) {
                exampleViewModel.fetchWeather()
                if(exampleViewModel.weather.value != null){
                    exampleViewModel.fetchWeatherCity("Toronto")
                }
            } else {
                // Handle the situation when permissions are not granted
                exampleViewModel.fetchWeatherCity("Montreal")
            }
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
// Add a timer to fetch weather  get location and spare some time to get weather.


        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val weather by exampleViewModel.weather.observeAsState(null)
                    val weatherType = weather?.code?.let { WeatherType.fromWMO(it) }

                    WeatherAppLayout2(weather = weather, weatherType = weatherType,viewModel = exampleViewModel)
                    //Greeting(name = "Weather App", weather = weather)


                }
            }
        }


    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherAppLayout2(weather: Weather?, weatherType: WeatherType?, viewModel: MainViewModel) {

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),

                verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (weather == null) {
                    Text(
                        text = "Couldn't get Location",
                    )
                    TextFieldWithIcons(viewModel)
                } else {
                    //Mock search bar
                    //experimental design 2
                    //SearchPill()
                    TextFieldWithIcons(viewModel)


                    Text(
                        text = "${weather.city_name}",
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    // Weather icon


                    weatherType?.let {
                        Icon(
                            painter = painterResource(id = it.iconRes),
                            contentDescription = it.weatherDesc,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    // Weather temperature
                    Text(
                        text = "${weather.temp?.toInt()}°C",
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(top = 1.dp)
                    )



                    // Weather description
                    Text(
                        text = "${weather.description}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    // Other weather details
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 80.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Humidity
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Humidity",
                                style = MaterialTheme.typography.caption ,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "${weather.rh}%",
                                style = MaterialTheme.typography.body2,
                                fontSize = 18.sp
                            )
                        }

                        // Wind speed
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Wind Speed",
                                style = MaterialTheme.typography.caption,
                                fontSize = 18.sp

                            )
                            Text(
                                text = "${weather.wind_spd?.toInt()} Km/h",
                                style = MaterialTheme.typography.body2,
                                fontSize = 18.sp
                            )
                        }

                        // UV index
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "UV Index",
                                style = MaterialTheme.typography.caption,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "${weather.uv?.toInt()}",
                                style = MaterialTheme.typography.body2,
                                fontSize = 18.sp
                            )
                        }
                    }
                    WeatherUpdateCard(
                        WeatherCard( time="15 minutes ago",
                            message = "The air quality is ideal for most individuals; enjoy your normal outdoor activities use some sun screen.",0.0)
                    )
                }
            }
        }
    )
}


//This function will only get weather for the given city name when you press enter on keyboard of the phone
//It wont be making calls as you type.
// I implemented this function to handle both searching for other cities
// or getting weather from user input in the case of no access to location.
@Composable
fun TextFieldWithIcons(viewModel: MainViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()

    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon",
            modifier = Modifier
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                .size(20.dp)
        ) },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            text = it
        },
        label = { Text(text = "Enter City (┛◉Д◉)┛彡┻━┻") },
        placeholder = { Text(text = "Enter City (┛◉Д◉)┛彡┻━┻") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                coroutineScope.launch {
                    fetchWeatherForCity(text.text,viewModel)
                    println("Entered Text is: ${text.text}")
                }
            }
        )
    )
}

fun fetchWeatherForCity(city: String,viewModel: MainViewModel) {
    viewModel.fetchWeatherCity(city)
}


//used for testing displaying of weather data
/*
@Composable
fun Greeting(name: String, weather: Weather?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (weather == null) {
            Text("Loading weather...")
        } else {
            Text("Current temperature: ${weather.temp}")
            Text("UV index: ${weather.uv}")
            Text("Wind speed: ${weather.wind_spd}")
            // Text("Precipitation: ${weather.percip}") // There is no percip field in the Weather data class
            Text("Relative humidity: ${weather.rh}")
            Text("Feels like: ${weather.app_temp}")
            Text("Weather code: ${weather.code}")
        }
    }

    Text(text = "Hello $name!")
}
*/