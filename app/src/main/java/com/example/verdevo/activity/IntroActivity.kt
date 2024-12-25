package com.example.verdevo.activity

import VerdevoButton
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.verdevo.R
import com.example.verdevo.ui.theme.Green
import com.example.verdevo.ui.theme.Typography

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        setContent {
        }
    }
}

@Composable
@Preview
private fun IntroScreen(modifier: Modifier = Modifier, onClick : () -> Unit) {
    Column(modifier = modifier
        .fillMaxSize()
        .fillMaxWidth()
        .background(Color.White)
        .verticalScroll(rememberScrollState())) {

        Box(modifier = modifier.fillMaxWidth()
            .height(400.dp)
            .background(Green,
            shape = RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 20.dp))) {

            Image(modifier = modifier.align(Alignment.TopCenter)
                .size(300.dp),
                painter = painterResource(R.drawable.background),
                contentDescription = "Imagem de fundo")

            Spacer(modifier = modifier.height(5.dp))

            Text(modifier = modifier.padding(top = 300.dp, start = 100.dp),
                text = "Verdevo", style = Typography.headlineLarge, color = Color.White)

        }

        Column(modifier = modifier.fillMaxSize()
            .padding(10.dp)) {
                Text(modifier = modifier.padding(top = 10.dp, start = 42.dp, end = 10.dp),
                    text = stringResource(R.string.first_introduction), style = Typography.headlineMedium, color = Color.Black)
                Text(modifier = modifier.padding(start = 32.dp, end = 10.dp),
                    text = stringResource(R.string.first_first_introduction), style = Typography.headlineMedium, color = Color.Black)
                Text(modifier = modifier.padding(top = 10.dp, start = 22.dp, end = 10.dp),
                    text = stringResource(R.string.second_introduction), style = Typography.bodyLarge, color = Color.Black)

                VerdevoButton(modifier = modifier.widthIn(320.dp)
                    .padding(top = 35.dp, start = 45.dp),
                    text = stringResource(R.string.start),
                    onClick = {})
        }
    }
}