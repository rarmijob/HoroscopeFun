package dev.rarmijo.horoscopefun.presentation.screens.roulette_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rarmijo.horoscopefun.R
import dev.rarmijo.horoscopefun.ui.theme.OrangeMystic
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RouletteScreen(
    modifier: Modifier = Modifier,
    navToLuck: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var myHeight by remember { mutableIntStateOf(0) }

    var isTwinkling by remember { mutableStateOf(true) }

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

            isTwinkling = false
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
                targetValue = -200f,
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
            .fillMaxSize()
    ) {

        TwinklingText(isTwinkling = isTwinkling, modifier = modifier.align(Alignment.Center))

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

@Composable
fun TwinklingText(isTwinkling: Boolean, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "Transition Twinkling")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(offsetMillis = 1000)
        ), label = "Alpha Twinkling"
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.drag_spin),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 32.sp,
            modifier = Modifier.alpha(if (isTwinkling) alpha else 0f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            imageVector = Icons.Filled.ArrowDownward,
            contentDescription = "arrow downward",
            tint = OrangeMystic,
            modifier = Modifier
                .scale(1.5f)
                .alpha(if (isTwinkling) alpha else 0f)
        )

    }
}


@Preview(showBackground = true)
@Composable
private fun RoulettePreview() {
    RouletteScreen(navToLuck = {})
}