package dev.rarmijo.horoscopefun.presentation.screens.luck_screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.ui.theme.BlackerSmoke
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic

@Composable
fun LuckScreen(
    modifier: Modifier = Modifier,
    state: LuckState,
    navBack: () -> Unit,
) {

    val context = LocalContext.current

        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

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
                    if (state.cardTitle != 0) {
                        Text(
                            text = stringResource(id = state.cardTitle),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            //TODO improve size of image
            if (state.cardImage != 0) {
                Image(
                    painter = painterResource(id = state.cardImage),
                    contentDescription = "card back small",
                    modifier = modifier.size(300.dp, 500.dp),
                )
            }

            if (state.cardDescription != 0) {
                val description = stringResource(id = state.cardDescription)
                Box(modifier = modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Button(
                    onClick = { sharePrediction(context, description) },
                    colors = ButtonDefaults.buttonColors(containerColor = BlackerSmoke),
                ) {
                    Text(text = stringResource(id = R.string.share))
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
            cardImage = R.drawable.card_king_cups
        ),
        navBack = {}
    )
}


private fun sharePrediction(context: Context, prediction: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, prediction)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "prediction")
    context.startActivity(shareIntent)
}