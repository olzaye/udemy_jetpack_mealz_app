package com.soenmez.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.soenmez.mealzapp.navigation.MealNavigationType
import com.soenmez.mealzapp.navigation.NavigationRoute
import com.soenmez.mealzapp.ui.details.MealDetailsScreen
import com.soenmez.mealzapp.ui.details.MealDetailsViewModel
import com.soenmez.mealzapp.ui.details.scrollingAnimation.MealDetailScrollingAnimationScreen
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
        composable(route = NavigationRoute.StartDestination.route) {
            MealsCategoriesScreen { type ->
                when (type) {
                    is MealNavigationType.AnimateMealDetailByClick -> navController.navigate("destination_meal_details/${type.mealId}")
                    is MealNavigationType.AnimateMealDetailByScrolling -> navController.navigate("destination_meal_details_animation_by_scrolling/${type.mealId}")
                }
            }
        }
        composable(
            route = NavigationRoute.NavigateToClickAnimationPage.route,
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val vm: MealDetailsViewModel = koinViewModel()
            MealDetailsScreen(vm.mealState.value)
        }

        composable(
            route = NavigationRoute.NavigateToScrollingAnimationPage.route,
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val vm: MealDetailsViewModel =
                koinViewModel() // we can use the same viewModel since the content is the same
            MealDetailScrollingAnimationScreen(vm.mealState.value)
        }
    }
}