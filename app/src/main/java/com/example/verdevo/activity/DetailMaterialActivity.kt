package com.example.verdevo.activity

import VerdevoButton
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.verdevo.R
import com.example.verdevo.model.Material
import com.example.verdevo.ui.theme.Black
import com.example.verdevo.ui.theme.Gray
import com.example.verdevo.ui.theme.White

class DetailMaterialActivity : ComponentActivity() {

    private lateinit var material : Material

    override fun onCreate(savedInstanceState: Bundle?) {
        material = (intent.getSerializableExtra("object") as Material?)!!

        super.onCreate(savedInstanceState)
        setContent {
            DetailMaterialScreen(material, onBackClick = {finish()},
                onMeasureClick = {
                    val intent = Intent(this, MeasureActivity::class.java).apply {
                        putExtra("object", material)
                    }
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun DetailMaterialScreen(material: Material,  onBackClick : () -> Unit, onMeasureClick : () -> Unit) {

    val selectedImageUrl by remember { mutableStateOf(material.picUrl.first()) }
    var selectedModelIndex by remember { mutableIntStateOf(-1) }

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

            Image(
                painter = rememberAsyncImagePainter(model = selectedImageUrl),
                contentDescription = "Imagem selecionada",
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)
                    .background(
                        Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(top = 12.dp)
            )
        }

        Row(modifier = Modifier.fillMaxWidth()
            .padding(start = 7.dp, top = 10.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Gray, shape = RoundedCornerShape(10.dp))) {
                Column(modifier = Modifier.fillMaxSize()
                    .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Icon(
                        painter = painterResource(R.drawable.heat),
                        contentDescription = "Ícone de calor",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "Condutor de calor : " + if (material.heatConductor) "Sim" else "Não",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.livvic_regular)),
                        color = Black
                    )

                }
            }
            Box(modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Gray, shape = RoundedCornerShape(10.dp))) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.lightning),
                        contentDescription = "Ícone de eletricidade",
                        modifier = Modifier.size(40.dp)

                    )
                    Text(
                        text = "Condutor de eletricidade : " + if (material.eletricityConductor) "Sim" else "Não",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.livvic_regular)),
                        color = Black
                    )
                }
            }
            Box(modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Gray, shape = RoundedCornerShape(10.dp))) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.work),
                        contentDescription = "Ícone de aplicação",
                        modifier = Modifier.size(40.dp)

                    )
                    Text(
                        text = "Aplicações : " + material.applications.first(),
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.livvic_regular)),
                        color = Black
                    )
                }
            }
        }
        Text(
            text = material.description, fontSize = 17.sp, color = Black,
            modifier = Modifier.padding(vertical = 19.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            VerdevoButton(modifier = Modifier.widthIn(350.dp)
                .padding(top = 15.dp, start = 5.dp),
                text = "Mensurar eficiência",
                onClick = {onMeasureClick()})
        }
    }
}