package com.soenmez.mealzapp.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.soenmez.mealzapp.model.response.MealResponse

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
@Composable
internal fun MealDetailsScreen(meal: MealResponse?) {
    val meal = meal ?: return

    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
    val transition = updateTransition(targetState = profilePictureState, label = "")

    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    val borderWidth by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {

                val request = ImageRequest.Builder(LocalContext.current).data(meal.categoryThumb)
                    .transformations(CircleCropTransformation()).build()
                Card(
                    modifier = Modifier.padding(16.dp),
                    shape = CircleShape,
                    border = BorderStroke(width = borderWidth, color = color)
                ) {
                    AsyncImage(
                        model = request,
                        contentDescription = "Meal Detail Pic",
                        modifier = Modifier
                            .size(imageSizeDp)
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
            Button(
                onClick = {
                    profilePictureState =
                        if (profilePictureState == MealProfilePictureState.Normal)
                            MealProfilePictureState.Expended
                        else
                            MealProfilePictureState.Normal
                }, modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Change State of meal profile picture",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

internal enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp), Expended(Color.Green, 200.dp, 24.dp)
}

@Preview(showBackground = true)
@Composable
fun MealDetailsScreenPreview() {
    MealDetailsScreen(
        MealResponse(
            id = "",
            categoryName = "Sample",
            categoryThumb = "https://www.themealdb.com/images/category/beef.png",
            categoryDescription = "Sample descriotion"
        )
    )
}