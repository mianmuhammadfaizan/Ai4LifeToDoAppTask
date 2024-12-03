package com.toDoApp.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toDoApp.storage.database.SellViewModel
import com.toDoApp.screens.tutorialsscreen.BuyListScreen
import com.toDoApp.screens.tutorialsscreen.CallListView
import com.toDoApp.screens.tutorialsscreen.DashBoardScreen
import com.toDoApp.screens.tutorialsscreen.SellListScreen
import com.toDoApp.viewmodels.CallListViewModel
import com.toDoApp.viewmodels.ProductListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation", "RestrictedApi", "ComposableDestinationInComposeScope")
fun NavGraphBuilder.dashboardScreen(navController: NavController) {


    composable(ToDoAppScreens.DashboardScannerScreen.name) {

        DashBoardScreen()
        { screenName ->
            when (screenName) {
                ToDoAppScreens.CallListView.name -> navController.navigate(ToDoAppScreens.CallListView.name)
                ToDoAppScreens.SellListScreen.name -> navController.navigate(ToDoAppScreens.SellListScreen.name)
                ToDoAppScreens.BuyListScreen.name -> navController.navigate(ToDoAppScreens.BuyListScreen.name)
                else -> {
                    navController.navigateUp()
                }
            }

        }
    }

    composable(ToDoAppScreens.CallListView.name) {
        val callListViewModel: CallListViewModel = hiltViewModel()
        CallListView(callListViewModel)
        { screenName ->
            when (screenName) {

                else -> {
                    navController.navigateUp()
                }
            }

        }
    }

    composable(ToDoAppScreens.BuyListScreen.name) {
        val productListViewModel: ProductListViewModel = hiltViewModel()
        BuyListScreen(productListViewModel)
        { screenName ->
            when (screenName) {

                else -> {
                    navController.navigateUp()
                }
            }

        }
    }
    composable(ToDoAppScreens.SellListScreen.name) {
        val viewModel: SellViewModel = hiltViewModel()
        SellListScreen(viewModel)
        { screenName ->
            when (screenName) {

                else -> {
                    navController.navigateUp()
                }
            }

        }
    }


}





