package io.lostimagin4tion.sovcombankchallenge.ui.components.items

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.lostimagin4tion.sovcombankchallenge.ui.components.charts.PieChart

@Composable
fun ItemAccountCardWithPieChart(
    modifier: Modifier = Modifier
) = Card(
    shape = MaterialTheme.shapes.small,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.onPrimary
    ),
    elevation = CardDefaults.cardElevation(5.dp),
    modifier = modifier
) {
    PieChart()
}