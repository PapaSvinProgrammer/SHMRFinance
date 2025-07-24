package com.example.charts

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charts.utils.generateColors
import kotlin.math.max

@Composable
fun DonutChart(
    data: Map<Float, String>,
    modifier: Modifier = Modifier,
    boundSize: Dp = 200.dp
) {
    val formatData = data.toList()
    val total = formatData.sumOf { it.first.toDouble() }.toFloat()
    val colors = generateColors(formatData.size)

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    }

    val minSweepAngle = 2f

    val sweepAngles = remember(formatData) {
        val rawAngles = formatData.map { (it.first / total) * 360f }
        val adjusted = rawAngles.map { max(it, minSweepAngle) }

        val overshoot = adjusted.sum() - 360f

        if (overshoot > 0f) {
            val reductionFactor = 360f / adjusted.sum()
            adjusted.map { it * reductionFactor }
        } else {
            adjusted
        }
    }

    Box(
        modifier = modifier
            .size(boundSize)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = 30f
            val radius = size.minDimension / 2
            val topLeft = Offset(
                (size.width - radius * 2) / 2,
                (size.height - radius * 2) / 2
            )

            var startAngle = -90f

            formatData.forEachIndexed { index, _ ->
                val sweep = sweepAngles[index] * animatedProgress.value

                drawArc(
                    color = colors[index % colors.size],
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    style = Stroke(width = stroke, cap = StrokeCap.Butt),
                    size = Size(radius * 2, radius * 2),
                    topLeft = topLeft
                )

                startAngle += sweepAngles[index]
            }
        }

        Column(horizontalAlignment = Alignment.Start) {
            formatData.take(3).forEachIndexed { index, (_, label) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(
                                color = colors[index % colors.size],
                                shape = CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 13.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

