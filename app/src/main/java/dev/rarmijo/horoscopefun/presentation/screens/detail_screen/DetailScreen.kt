package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import dev.rarmijo.horoscopefun.ui.theme.BlackSmoke
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic
import dev.rarmijo.horoscopefun.ui.theme.Transparent

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    navBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(BlackSmoke)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.showError) {

            Text(
                text = state.errorMsg,
                style = MaterialTheme.typography.titleLarge,
                color = OrangeMystic
            )
        } else {


            Box(modifier = modifier
                .background(BlackSmoke)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = OrangeMystic,
                    modifier = modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .clickable { navBack() }
                )

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Transparent)
                        .padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = state.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = OrangeMystic
                    )

                    state.horoscopeInfo?.let { painterResource(id = it.img) }
                        ?.let { Image(painter = it, contentDescription = state.title) }


                    Box(
                        modifier = modifier
                            .background(BlackSmoke)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = OrangeMystic
                        )
                    }
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
        ),
        navBack = {}
    )

    //}

}