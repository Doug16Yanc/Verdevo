package com.example.verdevo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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
            DetailMaterialScreen(modifier = Modifier, material, onBackClick = {finish()}
                )
        }
    }
}

@Composable
fun DetailMaterialScreen(modifier: Modifier = Modifier,
                         material: Material,
                         onBackClick : () -> Unit) {
    val selectedImageUrl by remember { mutableStateOf(material.picUrl.first()) }
    var selectedModelIndex by remember { mutableIntStateOf(-1) }

    Column(modifier = modifier.fillMaxSize()
        .background(White)
        .verticalScroll(rememberScrollState())
        .padding(20.dp)
    ) {
        ConstraintLayout(modifier = modifier.padding(top = 25.dp, bottom = 17.dp)
            .fillMaxWidth()) {
                val (back) = createRefs()
                Image(painter = painterResource(R.drawable.back),
                    contentDescription = "Imagem de retorno",
                    modifier = modifier.clickable { onBackClick()}
                        .constrainAs(back) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                        })
            Image(painter = rememberAsyncImagePainter(model = selectedImageUrl),
                contentDescription = "Imagem selecionada",
                modifier = modifier.fillMaxWidth()
                    .height(250.dp)
                    .background(Gray,
                        shape = RoundedCornerShape(12.dp))
                .padding(22.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 16.dp)) {
            Text(
                text = material.name,
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(R.font.livvic_bold)),
                color = Black,
                modifier = modifier.fillMaxWidth()
                    .weight(2f)
                    .padding(end = 22.dp)
            )
        }
        Text(text = material.description, fontSize = 17.sp, color = Black,
            modifier = modifier.padding(vertical = 19.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()) {
        }
    }
}