package dev.rarmijo.horoscopefun.presentation.screens.horoscope_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.ui.theme.Blue
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic
import kotlinx.coroutines.delay


@Composable
fun HoroscopeScreen(
    modifier: Modifier = Modifier,
    state: HoroscopeState,
    navToDetail: (HoroscopeInfo) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = OrangeMystic,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            var clickEnabled by remember { mutableStateOf(true) }

            LaunchedEffect(clickEnabled) {
                if (!clickEnabled) {
                    delay(500L)
                    clickEnabled = true
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {

                state.horoscopeInfoList.forEach {
                    item {
                        HoroscopeItem(
                            info = it,
                            clickEnabled = clickEnabled,
                            navToDetail = navToDetail
                        )
                    }
                }
            }
        }
    }
}







@Composable
fun HoroscopeItem(
    info: HoroscopeInfo,
    clickEnabled: Boolean,
    navToDetail: (HoroscopeInfo) -> Unit,
) {
    val name = stringResource(id = info.name)

    Card(
        modifier = if (clickEnabled) {
            Modifier.clickable { navToDetail(info) }
        } else {
            Modifier
        }
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(width = 1.dp, color = OrangeMystic),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name, style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = info.icon),
                contentDescription = name,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HoroscopePreview() {

    HoroscopeScreen(
        modifier = Modifier,
        state = HoroscopeState(
            isLoading = false,
            horoscopeInfoList =
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
        ),
        navToDetail = {}
    )
}

