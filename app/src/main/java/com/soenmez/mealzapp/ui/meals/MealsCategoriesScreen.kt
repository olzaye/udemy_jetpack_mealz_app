package com.soenmez.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soenmez.mealzapp.model.response.MealResponse
import com.soenmez.mealzapp.repository.FakeMealRepositoryImpl
import com.soenmez.mealzapp.ui.theme.MealzAppTheme
import org.koin.androidx.compose.koinViewModel

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */


@Composable
internal fun MealsCategoriesScreen(
    vm: MealsCategoriesViewModel = koinViewModel(),
    navigationCallback: (String) -> Unit
) {
    //val vm = koinViewModel<MealsCategoriesViewModel>()
    val meals = vm.mealsCategoriesFlow.collectAsState()
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals.value) {
            MealCategory(it, navigationCallback)
        }
    }
}

@Composable
internal fun MealCategory(
    meal: MealResponse,
    navigationCallback: (String) -> Unit
) {
    var isExpended by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable { navigationCallback(meal.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            AsyncImage(
                model = meal.categoryThumb,
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(text = meal.categoryName, style = MaterialTheme.typography.titleMedium)

                CompositionLocalProvider(
                    LocalContentColor provides LocalContentColor.current.copy(
                        alpha = 0.6f
                    )
                ) {
                    Text(
                        text = meal.categoryDescription,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpended) 10 else 4
                    )
                }
            }
            Icon(
                imageVector = if (isExpended) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(if (isExpended) Alignment.Bottom else Alignment.CenterVertically)
                    .clickable { isExpended = !isExpended }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealzAppTheme {
        MealsCategoriesScreen(MealsCategoriesViewModel(FakeMealRepositoryImpl())) {}
    }
}