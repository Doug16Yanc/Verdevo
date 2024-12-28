package com.example.verdevo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.verdevo.R
import com.example.verdevo.ui.theme.Black
import com.example.verdevo.viewModel.MainViewModel

class ListTypeActivity : ComponentActivity() {

    private val viewModel = MainViewModel()
    private var id : String = ""
    private var title : String = ""
    private var description : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id") ?:""
        title = intent.getStringExtra("title") ?:""
        description = intent.getStringExtra("description") ?:""

        setContent {
            ListTypeScreen(title = title,
                description = description,
                onBackClick = {finish()},
                viewModel = viewModel,
                id = id)
        }
    }
}

@Composable
fun ListTypeScreen(title : String,
                   description : String,
                   onBackClick : () -> Unit,
                   viewModel: MainViewModel,
                   id : String) {

    val items by viewModel.trends.observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        viewModel.loadFiltered(id)
    }

    Box(modifier = Modifier
        .padding(start = 20.dp, top = 2.dp)) {
        Image(painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier.clickable { onBackClick() }
                .width(40.dp)
                .padding(top = 12.dp))
    }

    Column(modifier = Modifier.fillMaxSize().padding(17.dp)) {
        Text(modifier = Modifier.fillMaxWidth()
            .padding(top = 3.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.livvic_bold)),
         //   fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            text = title
            )

        Spacer(modifier = Modifier.height(1.dp))

        Column(modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp)) {
            Text(modifier = Modifier.padding(top = 6.dp),
                textAlign = TextAlign.Center,
                text = description,
                fontFamily = FontFamily(Font(R.font.livvic_medium)),
                fontSize = 16.sp,
                color = Black
                )
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxWidth()
                .padding(top = 30.dp),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else {
            ListMaterials(items)
        }
    }
    LaunchedEffect(items) {
        isLoading = items.isEmpty()
    }
}