package io.lostimagin4tion.sovcombankchallenge.ui.components.charts

import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

data class PieChartData(
    var currencyName: String?,
    var value: Float?,
    var color: Color
)

val getPieChartData = listOf(
    PieChartData("Rubble", 34.68F, Color(0xFF77C78D)),
    PieChartData("Dollar", 16.60F, Color(0xFFA26AA8)),
    PieChartData("Euro", 16.15F, Color(0xFFB5A76B)),
)


@Composable
fun PieChart() {
    Row(
        modifier = Modifier
            .padding(
                vertical = 10.dp,
                horizontal = 10.dp
            )
            .size(250.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Crossfade(targetState = getPieChartData) { pieChartData ->
            AndroidView(factory = { context ->
                PieChart(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    this.isDrawHoleEnabled = false

                    this.legend.isEnabled = true
                    this.legend.textSize = 14F

                    this.legend.horizontalAlignment =
                        Legend.LegendHorizontalAlignment.CENTER

                    this.description.isEnabled = false
                    this.setDrawEntryLabels(false)

                    // on below line we are specifying entry label color as white.
                    this.setEntryLabelColor(Color.Black.toArgb())
                }
            },
                modifier = Modifier.wrapContentSize(),
                update = {
                    updatePieChartWithData(it, pieChartData)
                })
        }
    }
}
fun updatePieChartWithData(
    chart: PieChart,
    data: List<PieChartData>
) {
    val entries = ArrayList<PieEntry>()

    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value ?: 0.toFloat(), item.currencyName ?: ""))
    }

    val ds = PieDataSet(entries, "")

    ds.colors = mutableListOf()

    for (pieChartData in data) {
        ds.colors.add(pieChartData.color.toArgb())
    }

    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    ds.valueTextColor = Color.White.toArgb()
    ds.valueTextSize = 14f
    ds.valueTypeface = Typeface.DEFAULT

    ds.sliceSpace = 0f

    val d = PieData(ds)

    chart.data = d

    chart.invalidate()
}