package com.soenmez.mealzapp.ui.details.scrollingAnimation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.soenmez.mealzapp.model.response.MealResponse
import java.lang.Float.min

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
@Composable
internal fun MealDetailScrollingAnimationScreen(meal: MealResponse?) {
    val meal = meal ?: return

    // enable this when using only Column and not LazyColumn
    // val scrollState = rememberScrollState()
    // val offset = min(1f, 1 - (scrollState.value / 600f))

    val scrollState = rememberLazyListState()
    val calculateOffsetWhileScrolling =
        remember { derivedStateOf { 1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex) } }

    val offset = min(
        1f,
        calculateOffsetWhileScrolling.value
    )
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset), label = "")

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    val request =
                        ImageRequest.Builder(LocalContext.current).data(meal.categoryThumb)
                            .transformations(CircleCropTransformation()).build()
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.Cyan)
                    ) {
                        AsyncImage(
                            model = request,
                            contentDescription = "Meal Detail Pic",
                            modifier = Modifier
                                .size(size)
                                .padding(8.dp)
                        )
                    }

                    Text(
                        text = meal.categoryName,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            // Can be tested with: AnimateWithScrollingColumn(scrollState)
            AddOnlyWithScrollingLazyColumn(scrollState)
        }
    }
}


@Composable
fun AddOnlyWithScrollingLazyColumn(scrollState: LazyListState) {
    val dummyList = (0..100).map { it.toString() }
    LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
        items(dummyList) {
            Text(text = it, modifier = Modifier.padding(24.dp))
        }
    }
}

@Composable
fun AnimateWithScrollingColumn(scrollState: ScrollState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
        Text(text = "This is a text element", modifier = Modifier.padding(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MealDetailsScreenPreview() {
    MealDetailScrollingAnimationScreen(
        MealResponse(
            id = "",
            categoryName = "Sample",
            categoryThumb = "https://www.themealdb.com/images/category/beef.png",
            categoryDescription = "Sample descriotion"
        )
    )
}