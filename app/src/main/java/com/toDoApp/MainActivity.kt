package com.toDoApp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.work.HiltWorker
import androidx.navigation.compose.rememberNavController
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.toDoApp.navigation.ToDoApplicationNavigation
import com.toDoApp.viewmodels.GetCallsUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {


            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                val navController = rememberNavController()

                ToDoApplicationNavigation(navController)

            }

        }
    }


}

@HiltWorker
class ApiWorker @Inject constructor(
    @ApplicationContext context: Context,
    workerParams: WorkerParameters,
    private val getCallsUseCase: GetCallsUseCase // Inject your use case
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Call your API here
            val calls = getCallsUseCase() // Your use case to fetch data
            // Log the data (you can use Logcat or any logging mechanism)
            Log.d("aaaaaaaaa", "doWork:Fetched Calls: $calls ")
            Result.success()
        } catch (e: Exception) {
            // Handle any exceptions
            Log.d("aaaaaaaaaa", "doWork:Fetched Calls: Fail ")

            println("Error fetching calls: ${e.message}")
            Result.retry() // Retry if there's an error
        }
    }
}

fun scheduleApiWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<ApiWorker>(15, TimeUnit.MINUTES)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}