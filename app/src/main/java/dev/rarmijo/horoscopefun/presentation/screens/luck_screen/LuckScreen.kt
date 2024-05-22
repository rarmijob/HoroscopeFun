package dev.rarmijo.horoscopefun.presentation.screens.luck_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.R
import kotlinx.coroutines.launch

@Composable
fun LuckScreen(
    modifier: Modifier = Modifier,
    state: LuckState
) {

    Box(
        modifier = modifier.fillMaxSize(),
    ) {

        if (state.showLuck) {

        }



        val scope = rememberCoroutineScope()
        var myHeight by remember { mutableIntStateOf(0) }

        val rotationAngle = remember { Animatable(0f) }
        val slideOffset = remember { Animatable(0f) }
        val cardAlpha = remember { Animatable(0f) }
        val cardScale = remember { Animatable(1.25f) }


        val draggableState = rememberDraggableState { _ ->
            val randomDegree = (360..360 * 5).random()
            scope.launch {
                rotationAngle.animateTo(
                    targetValue = rotationAngle.value + randomDegree,
                    animationSpec = tween(
                        durationMillis = randomDegree * 2, easing = FastOutSlowInEasing
                    )
                )

                cardAlpha.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )

                slideOffset.animateTo(
                    targetValue = -300f,
                    animationSpec = tween(
                        durationMillis = randomDegree * 2, easing = FastOutSlowInEasing
                    )
                )

                cardScale.animateTo(
                    targetValue = 3f
                )
            }
        }


        Image(
            painter = painterResource(id = R.drawable.ruleta),
            contentDescription = "cards roulette",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .onGloballyPositioned {
                    myHeight = it.size.height
                }
                .offset(y = LocalDensity.current.run { (myHeight / 2).toDp() })
                .graphicsLayer {
                    rotationZ = rotationAngle.value
                }
                .draggable(draggableState, Orientation.Horizontal)


        )

        Image(
            painter = painterResource(id = R.drawable.card_back_small),
            contentDescription = "card back small",
            alpha = cardAlpha.value,
            modifier = modifier

                .align(Alignment.BottomCenter)
                .offset(y = slideOffset.value.dp)
                .scale(cardScale.value)
                .padding(bottom = 100.dp)

        )

    }

}



@Composable
fun LuckPrediction(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

    }

}

@Preview(showBackground = true)
@Composable
private fun LuckScreenPreview() {
    LuckScreen(state = LuckState(showLuck = false))
}

