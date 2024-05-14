package dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo

@Composable
fun HoroscopeScreen(
    modifier: Modifier = Modifier,
    state: HoroscopeState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            state.horoscopeInfoList.forEach {
                item {
                    HoroscopeItem(info = it)
                }
            }
        }
    }
}

@Composable
fun HoroscopeItem(info: HoroscopeInfo) {

    val name = LocalContext.current.resources.getString(info.name)

    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = Color.White)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.Black)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = name, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = info.img), contentDescription = name)
        }

    }


}


@Preview
@Composable
private fun HoroscopePreview() {

    HoroscopeScreen(
        modifier = Modifier.background(Color.Blue),
        state = HoroscopeState(
            listOf(
                HoroscopeInfo.Aries,
                HoroscopeInfo.Taurus,
                HoroscopeInfo.Gemini,
                HoroscopeInfo.Cancer,
                HoroscopeInfo.Leo,
                HoroscopeInfo.Virgo,
                HoroscopeInfo.Libra,
                HoroscopeInfo.Scorpio,
                HoroscopeInfo.Sagittarius,
                HoroscopeInfo.Capricorn,
                HoroscopeInfo.Aquarius,
                HoroscopeInfo.Pisces
            )
        )
    )


}