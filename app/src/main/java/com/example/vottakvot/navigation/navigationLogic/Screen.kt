package com.example.vottakvot.navigation.navigationLogic

sealed class  Screen(
    val route: String
) {
    object Splash : Screen(route = ROUTE_SPLASH_SCREEN)
    object Loader : Screen(route = ROUTE_LOADER_SCREEN)
    object FindWorkouts : Screen(route = ROUTE_FIND_WORKOUTS_SCREEN)
    object Welcome : Screen(route = ROUTE_WELCOME_SCREEN)
    object Inquirer : Screen(route = ROUTE_INQUIRER_SCREEN)

    object Home : Screen(route = ROUTE_HOME_SCREEN)
    object Exercise  : Screen(route = ROUTE_EXERCISE_SCREEN)
    object Workout : Screen(route = ROUTE_WORKOUT_SCREEN)

    object HomeWithoutOnboarding : Screen(route = ROUTE_HOME_WITHOUT_ONBOARDING_SCREEN)
    object Filter : Screen(route = ROUTE_FILTER_SCREEN)
    object SearchResult : Screen(route = ROUTE_SEARCH_RESULT_SCREEN)

    object SearchResultForYou : Screen(route = ROUTE_SEARCH_RESULT_FOR_YOU_SCREEN)
    object SearchResultPopular : Screen(route = ROUTE_SEARCH_RESULT_POPULAR_SCREEN)

    //----------WORKOUT------TYPE---------------
    object SearchResultCharger : Screen(route = ROUTE_SEARCH_CHARGER_SCREEN)
    object SearchResultHomeFitness : Screen(route = ROUTE_SEARCH_HOME_FITNESS_SCREEN)
    object SearchResultWorkFitness : Screen(route = ROUTE_SEARCH_WORK_FITNESS_SCREEN)
    object SearchResultBeforeBedtime: Screen(route = ROUTE_SEARCH_BEFORE_BEDTIME_SCREEN)

    //-------------------------------------------
    object MyTrains : Screen(route = ROUTE_MY_TRAINS_SCREEN)
    object EditOneTrain : Screen(route = ROUTE_EDIT_ONE_TRAIN_SCREEN)

    object Favourite : Screen(route = ROUTE_FAVOURITE_SCREEN)
    object Profile : Screen(route = PROFILE_SCREEN)

    private companion object {
        const val ROUTE_SPLASH_SCREEN = "splash_screen"
        const val ROUTE_LOADER_SCREEN = "loader_screen"
        const val ROUTE_FIND_WORKOUTS_SCREEN = "find_workouts_screen"
        const val ROUTE_WELCOME_SCREEN = "welcome_screen"
        const val ROUTE_INQUIRER_SCREEN = "inquirer_screen"
        const val ROUTE_HOME_SCREEN = "home_screen"
        const val ROUTE_EXERCISE_SCREEN = "exercise_screen"
        const val ROUTE_WORKOUT_SCREEN = "workout_screen"
        const val ROUTE_HOME_WITHOUT_ONBOARDING_SCREEN = "home_with_onboarding_screen"
        const val ROUTE_FILTER_SCREEN = "filter_screen"
        const val ROUTE_SEARCH_RESULT_SCREEN = "search_result_screen"
        const val ROUTE_SEARCH_RESULT_FOR_YOU_SCREEN = "search_result_for_you_screen"
        const val ROUTE_SEARCH_RESULT_POPULAR_SCREEN = "search_result_popular_screen"
        //-------------------------------
        const val ROUTE_SEARCH_CHARGER_SCREEN = "search_result_charger_screen"
        const val ROUTE_SEARCH_HOME_FITNESS_SCREEN = "search_result_home_fitness_screen"
        const val ROUTE_SEARCH_WORK_FITNESS_SCREEN = "search_result_work_fitness_screen"
        const val ROUTE_SEARCH_BEFORE_BEDTIME_SCREEN = "search_result_before_bedtime_screen"
        //-------------------------------

        const val ROUTE_MY_TRAINS_SCREEN = "my_trains_screen"
        const val ROUTE_EDIT_ONE_TRAIN_SCREEN = "edit_one_train_screen"
        const val ROUTE_FAVOURITE_SCREEN = "favourite_screen"
        const val PROFILE_SCREEN = "profile_screen"
    }

}