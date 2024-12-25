package com.example.verdevo.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.verdevo.model.Material
import com.example.verdevo.model.Slider
import com.example.verdevo.model.Type
import com.example.verdevo.ui.theme.Gray
import com.example.verdevo.ui.theme.Green
import com.example.verdevo.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel = MainViewModel()
    val banners = remember { mutableStateListOf<Slider>() }
    val types = remember { mutableListOf<Type>() }
    val trends = remember { mutableStateListOf<Material>() }
    var showBannerLoading by remember { mutableStateOf(true) }
    var showTypeLoading by remember { mutableStateOf(true) }
    var showMaterialLoading by remember { mutableStateOf(true) }

    //Banners
    LaunchedEffect(Unit) {
        viewModel.loadBanners()
        viewModel.banners.observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    //Material types
    LaunchedEffect(Unit) {
        viewModel.loadTypes()
        viewModel.types.observeForever {
            types.clear()
            types.addAll(it)
            showTypeLoading = false
        }
    }

    //Trends
    LaunchedEffect(Unit) {
        viewModel.loadTrends()
        viewModel.trends.observeForever {
            trends.clear()
            trends.addAll(it)
            showMaterialLoading = false
        }
    }

    Column(modifier = modifier.background(Color.White)) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            item {
                if (showBannerLoading) {
                    Box(modifier = modifier
                        .fillMaxSize()
                        .height(200.dp),
                        contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                    }
                }
                else{
                    Banners(modifier, banners)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banners(modifier: Modifier = Modifier, banners : List<Slider>) {
    AutoSlidingCarousel(banners = banners)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoSlidingCarousel(modifier: Modifier = Modifier, banners: List<Slider>) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState) {
        while (true) {
            if (!isDragged) {
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % banners.size)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .size(250.dp)
            .padding(top = 50.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(19.dp))
                .background(Green)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(banners[page].url)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
        Spacer(modifier = modifier.height(17.dp))

        if (banners.isNotEmpty()) {
            DotIndicator(
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .align(Alignment.CenterHorizontally),
                totalDots = banners.size,
                selectedIndex = pagerState.currentPage,
                dotSize = 10.dp
            )
        }
    }
}

@Composable
fun DotIndicator(modifier: Modifier = Modifier, totalDots : Int, selectedIndex : Int,
                 selectedColor : Color = Green, unSelectedColor : Color = Gray,
                 dotSize : Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) {
            index -> IndicatorDot(
                color = if(index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = modifier.padding(horizontal = 4.dp))
            }
        }
    }
}

@Composable
fun IndicatorDot(modifier: Modifier = Modifier, color : Color, size : Dp) {
    Box(modifier = modifier.size(size)
        .clip(CircleShape)
        .background(color))
}