package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.showError) {
            //TODO improve UI
            Text(
                text = state.errorMsg,
                style = MaterialTheme.typography.titleLarge,
            )
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize(),
                //verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = state.title,
                    style = MaterialTheme.typography.headlineLarge,
                )

                state.horoscopeInfo?.let { painterResource(id = it.img) }
                    ?.let { Image(painter = it, contentDescription = state.title) }


                Box(modifier = modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = state.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview(@PreviewParameter(LoremIpsum::class) description: String) {
    //HoroscopeFunTheme {
    DetailScreen(
        state = DetailState(
            isLoading = false,
            title = "Gemini",
            horoscopeInfo = HoroscopeInfo.Gemini,
            //first 100 words
            description = description.take(500)
        )
    )

    //}

}