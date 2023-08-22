package com.soenmez.mealzapp.navigation

internal sealed class NavigationRoute(val route: String) {
    internal object StartDestination : NavigationRoute("destination_meals_list")
    internal object NavigateToClickAnimationPage :
        NavigationRoute("destination_meal_details/{meal_category_id}")

    internal object NavigateToScrollingAnimationPage :
        NavigationRoute("destination_meal_details_animation_by_scrolling/{meal_category_id}")
}