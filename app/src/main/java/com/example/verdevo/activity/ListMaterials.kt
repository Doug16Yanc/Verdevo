package com.example.verdevo.activity

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.verdevo.R
import com.example.verdevo.model.Material
import com.example.verdevo.ui.theme.Black
import com.example.verdevo.ui.theme.Gray

@Composable
fun ListMaterials(modifier: Modifier = Modifier, items : List<Material>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .height(720.dp)
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        items(items.size) { row ->
            Row(modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TrendingMaterial(modifier, items, row)
            }

        }
    }
}

@Composable
fun TrendingMaterial(modifier: Modifier = Modifier, items : List<Material>, pos : Int) {
    val context = LocalContext.current
    Column(modifier = modifier
        .padding(11.dp)
        .height(160.dp)) {
        AsyncImage(model = items[pos].picUrl.first(), contentDescription = items[pos].name,
            modifier = modifier.width(180.dp)
                .background(Gray,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(180.dp)
                .padding(20.dp)
                .clickable {
                    val intent = Intent(context, DetailMaterialActivity::class.java).apply {
                        putExtra("object", items[pos])
                    }
                    startActivity(context, intent, null)
                },
            contentScale = ContentScale.Crop
        )

        Text(text = items[pos].name,
            color = Black,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.livvic_bold)),
            overflow = TextOverflow.Ellipsis,
            modifier = modifier.padding(top = 11.dp)
            )

        Row(modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        }
    }
}