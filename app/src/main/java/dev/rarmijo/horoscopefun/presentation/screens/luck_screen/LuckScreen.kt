package dev.rarmijo.horoscopefun.presentation.screens.luck_screen

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.ui.theme.BlackSmoke
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic
import java.util.Locale

@Composable
fun LuckScreen(
    modifier: Modifier = Modifier,
    state: LuckState,
    navBack: () -> Unit,
) {
    Box (modifier = modifier.background(BlackSmoke)){
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            if (state.cardTitle != 0) {
                Text(
                    text = stringResource(id = state.cardTitle).uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.headlineMedium,
                    color = OrangeMystic
                )
            }
            if (state.cardImage != 0) {
                Image(
                    painter = painterResource(id = state.cardImage),
                    contentDescription = "card back small"
                )
            }

            if (state.cardDescription != 0) {
                Box(modifier = modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = state.cardDescription),
                        style = MaterialTheme.typography.bodyLarge,
                        color = OrangeMystic
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun LuckScreenPreview() {
    LuckScreen(
        state = LuckState(
            cardTitle = R.string.justice,
            cardDescription = R.string.luck_26,
            cardImage = R.drawable.card_devil
        ),
        navBack = {}
    )
}

