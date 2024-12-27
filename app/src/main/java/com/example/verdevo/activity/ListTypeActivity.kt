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
import com.example.verdevo.viewModel.MainViewModel

class ListTypeActivity : ComponentActivity() {

    private val viewModel = MainViewModel()
    private var id : String = ""
    private var title : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id") ?:""
        title = intent.getStringExtra("title") ?:""

        setContent {
            ListTypeScreen(title = title,
                onBackClick = {finish()},
                viewModel = viewModel,
                id = id)
        }
    }
}

@Composable
fun ListTypeScreen(modifier: Modifier = Modifier,
                   title : String,
                   onBackClick : () -> Unit,
                   viewModel: MainViewModel,
                   id : String) {

    val items by viewModel.trends.observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        viewModel.loadFiltered(id)
    }

    Box(modifier = modifier
        .padding(start = 20.dp, top = 2.dp)) {
        Image(painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = modifier.clickable { onBackClick() }
                .width(40.dp)
                .padding(top = 12.dp))
    }

    Column(modifier = modifier.fillMaxSize().padding(17.dp)) {
        Text(modifier = modifier.fillMaxWidth()
            .padding(top = 3.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.livvic_bold)),
         //   fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            text = title
            )

        Spacer(modifier = modifier.height(17.dp))

        if (isLoading) {
            Box(modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        else {
            ListMaterials(modifier, items)
        }
    }
    LaunchedEffect(items) {
        isLoading = items.isEmpty()
    }
}