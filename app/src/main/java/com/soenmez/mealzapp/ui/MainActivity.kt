package com.soenmez.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.soenmez.mealzapp.ui.details.MealDetailsScreen
import com.soenmez.mealzapp.ui.details.MealDetailsViewModel
import com.soenmez.mealzapp.ui.meals.MealsCategoriesScreen
import com.soenmez.mealzapp.ui.theme.MealzAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                MealAppNavigation()
            }
        }
    }
}

@Composable
private fun MealAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen { id ->
                navController.navigate("destination_meal_details/$id")
            }
        }
        composable(
            route = "destination_meal_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val vm: MealDetailsViewModel = koinViewModel()
            MealDetailsScreen(vm.mealState.value)
        }
    }
}