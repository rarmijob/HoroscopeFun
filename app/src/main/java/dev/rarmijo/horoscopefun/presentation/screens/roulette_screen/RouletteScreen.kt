package dev.rarmijo.horoscopefun.presentation.screens.roulette_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.presentation.screens.luck_screen.LuckState
import dev.rarmijo.horoscopefun.ui.theme.BlackSmoke
import kotlinx.coroutines.launch

@Composable
fun RouletteScreen(
    modifier: Modifier = Modifier,
    navToLuck: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var myHeight by remember { mutableIntStateOf(0) }

    val rotationDegrees = remember {
        Animatable(0f)
    }
    val slideOffset = remember {
        Animatable(0f)
    }
    val cardAlpha = remember {
        Animatable(0f)
    }
    val cardScale = remember {
        Animatable(1.25f)
    }

    val draggableState = rememberDraggableState { _ ->
        val randomDegree = (360..360 * 5).random()
        scope.launch {
            rotationDegrees.animateTo(
                targetValue = rotationDegrees.value + randomDegree,
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
                targetValue = -250f,
                animationSpec = tween(
                    durationMillis = 1500, easing = FastOutSlowInEasing
                )
            )

            cardScale.animateTo(
                targetValue = 3f
            )
            navToLuck()
        }
    }

    Box(
        modifier = modifier
            .background(BlackSmoke)
            .fillMaxSize()
    ) {

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
                    rotationZ = rotationDegrees.value
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
