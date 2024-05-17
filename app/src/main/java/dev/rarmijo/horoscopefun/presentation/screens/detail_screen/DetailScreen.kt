package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.ui.theme.HoroscopeFunTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState
) {
    Column(
        modifier = modifier
            //.background(Color.Black)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineLarge,
            )

        state.horoscopeInfo?.let { painterResource(id = it.img) }
            ?.let { Image(painter = it, contentDescription = state.title) }
        Text(
            text = state.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    //HoroscopeFunTheme {
        DetailScreen(
            state = DetailState(
                isLoading = false,
                title = "Gemini",
                horoscopeInfo = HoroscopeInfo.Gemini,
                description = "This is a description"
            )
        )

    //}

}