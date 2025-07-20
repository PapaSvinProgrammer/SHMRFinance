package com.example.charts

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.charts.utils.PrettyValue
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.ColumnCartesianLayerModel
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.cartesian.marker.ColumnCartesianLayerMarkerTarget
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.component.LineComponent
import com.patrykandpatrick.vico.core.common.data.ExtraStore
import com.patrykandpatrick.vico.core.common.shape.CorneredShape
import java.math.BigDecimal

@Composable
fun ColumnChart(
    y: List<Float>,
    modifier: Modifier = Modifier
) {
    val modelProducer = remember { CartesianChartModelProducer() }

    LaunchedEffect(Unit) {
        modelProducer.runTransaction {
            columnSeries { series(y) }
        }
    }

    ColumnChartImpl(modelProducer, modifier)
}

@Composable
private fun ColumnChartImpl(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier = Modifier,
) {
    val positiveColumn = rememberLineComponent(
        fill = fill(MaterialTheme.colorScheme.primary),
        thickness = 8.dp,
        shape = CorneredShape.rounded(topLeftPercent = 40, topRightPercent = 40),
    )

    val negativeColumn = rememberLineComponent(
        fill = fill(Color(0xFFFF5F00)),
        thickness = 8.dp,
        shape = CorneredShape.rounded(bottomLeftPercent = 40, bottomRightPercent = 40),
    )

    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberColumnCartesianLayer(
                columnProvider = remember(positiveColumn, negativeColumn) {
                    getColumnProvider(positiveColumn, negativeColumn)
                },
                columnCollectionSpacing = 4.dp,
            ),
            bottomAxis = HorizontalAxis.rememberBottom(labelRotationDegrees = 45f),
            marker = rememberMarker(MarkerValueFormatter),
        ),
        modelProducer = modelProducer,
        modifier = modifier,
    )
}


private val MarkerValueFormatter = DefaultCartesianMarker.ValueFormatter { _, targets ->
    val value = (targets[0] as ColumnCartesianLayerMarkerTarget).columns[0]
    PrettyValue.getPrettyColumnValue(BigDecimal(value.entry.y))
}

private fun getColumnProvider(positive: LineComponent, negative: LineComponent) =
    object : ColumnCartesianLayer.ColumnProvider {
        override fun getColumn(
            entry: ColumnCartesianLayerModel.Entry,
            seriesIndex: Int,
            extraStore: ExtraStore,
        ) = if (entry.y >= 0) positive else negative

        override fun getWidestSeriesColumn(seriesIndex: Int, extraStore: ExtraStore) = positive
    }