package dev.rarmijo.horoscopefun.presentation.screens.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.domain.models.HoroscopeInfo
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    state: DetailState,
    navBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(color = OrangeMystic)
        } else if (state.showError) {

            Column(
                modifier = modifier.fillMaxWidth(0.7f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "error icon",
                    tint = OrangeMystic,
                    modifier = modifier
                        .scale(1.5f)
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.error_detected),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.error_code_indicator) + " " + state.errorMsg,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.try_again),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = modifier.height(16.dp))
                Button(
                    onClick = navBack,
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = OrangeMystic,
//                        contentColor = White
//                    )
                ) {
                    Text(text = "Go Back")
                }

            }


        } else {

            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {

                    Row {

                        Box(modifier = modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back",
                                tint = OrangeMystic,
                                modifier = modifier
                                    .align(Alignment.TopStart)
                                    .padding(16.dp)
                                    .scale(1.5f)
                                    .clickable { navBack() }
                            )

                            Text(
                                text = state.title,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }


                    state.horoscopeInfo?.let { painterResource(id = it.img) }
                        ?.let { Image(painter = it, contentDescription = state.title) }


                    Box(
                        modifier = modifier
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.description,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Justify,
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
            description = description.take(500),
            showError = false,
            errorMsg = "NoInternet"
        ),
        navBack = {}
    )

    //}

}