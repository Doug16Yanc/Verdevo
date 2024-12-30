package com.example.verdevo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.verdevo.R
import com.example.verdevo.model.Material
import com.example.verdevo.service.calculateEnergyEfficience
import com.example.verdevo.service.calculateOverlayColor
import com.example.verdevo.ui.theme.Black
import com.example.verdevo.ui.theme.Green
import com.example.verdevo.ui.theme.White

class MeasureActivity : ComponentActivity() {

    private lateinit var material : Material

    override fun onCreate(savedInstanceState: Bundle?) {
        material = (intent.getSerializableExtra("object") as Material?)!!

        super.onCreate(savedInstanceState)
        setContent {
            MeasureScreen(material, onBackClick = {finish()})
        }
    }
}

@Composable
fun MeasureScreen(material : Material, onBackClick : () -> Unit) {

    var temperature by remember { mutableFloatStateOf(27f) }
    var luminance by remember { mutableFloatStateOf(150f) }
    var area by remember { mutableFloatStateOf(10f) }
    var overlayColorWindow by remember { mutableStateOf(calculateOverlayColor(temperature)) }

    Column(
        modifier = Modifier.fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp, top = 2.dp)
            ) {
                Image(painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier.clickable { onBackClick() }
                        .width(40.dp))
            }
            Text(
                text = material.name,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.livvic_bold)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }
        Column(
            modifier = Modifier.padding(1.dp)
        ) {

            Box(modifier = Modifier.padding(start = 5.dp, top = 4.dp, end = 2.dp)
                .background(Black, shape = RoundedCornerShape(12.dp))
            ){
                Image(
                    painter = painterResource(R.drawable.window),
                    contentDescription = "Imagem da janela",
                    modifier = Modifier.fillMaxWidth()
                        .height(250.dp)
                        .background(
                            overlayColorWindow,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(top = 12.dp)
                )
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(text = "Mensurar eficiência energética para janelas climatizadas de 4mm a 6mm de espessura" +
                    "e 50% de transmitância luminosa nas películas do vidro.",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.livvic_bold)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp)
                )
            Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = "Temperatura ambiente: ${temperature.toInt()}°C",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.livvic_regular)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Slider(
                value = temperature,
                onValueChange = { temperature = it
                    overlayColorWindow = calculateOverlayColor(temperature)
                },
                valueRange = 1f..45f,
                steps = 14,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Green,
                    activeTrackColor = Green)
                )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Luminosidade por m² (lux): ${luminance.toInt()}",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.livvic_regular)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Slider(
                value = luminance,
                onValueChange = { luminance = it},
                valueRange = 50f..1000f,
                steps = 14,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Green,
                    activeTrackColor = Green)
                )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Área do ambiente (m²): ${area.toInt()}",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.livvic_regular)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Slider(
                value = area,
                onValueChange = { area = it},
                valueRange = 1f..500f,
                steps = 14,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Green,
                    activeTrackColor = Green))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = if (temperature <= 22 ) "Maior passagem de calor" else "Maior bloqueio de calor",
                color = Black,
                fontFamily = FontFamily(Font(R.font.livvic_bold)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Economia de energia em 1 hora (quilowatt-hora) : " + calculateEnergyEfficience(luminance, area),
                color = Black,
                fontFamily = FontFamily(Font(R.font.livvic_bold)),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())


        }
        }
    }
}

