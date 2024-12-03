package com.toDoApp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
import androidx.navigation.compose.rememberNavController
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.toDoApp.navigation.ToDoApplicationNavigation
import com.toDoApp.network.GetCallsUseCase
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

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


class ApiWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val getCallsUseCase: GetCallsUseCase
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        // Implement your API call logic here

        val response = getCallsUseCase.invoke()
        Log.d("aaaafffaa", "doWork: $response")
        // Handle the response, e.g., store data, update UI, etc.
        // ...

        return Result.success() // Or Result.failure() if there's an error
    }
}

//class ApiCoroutineWorker(
//    appContext: Context,
//    workerParams: WorkerParameters,
//    private val getCallsUseCase: GetCallsUseCase,
//) :
//    CoroutineWorker(appContext, workerParams) {
//
//    override suspend fun doWork(): Result {
//        return try {
//            // Call your suspend function here
//            val response = getCallsUseCase.invoke()
//            Log.d("aaaafffaa", "doWork: $response")
//            if (response.isNotEmpty()) {
//
//                Result.success()
//            } else {
//                Result.retry()
//            }
//        } catch (e: Exception) {
//            Result.failure()
//        }
//    }
//}

//@HiltWorker
//class ApiWorker @Inject constructor(
//    @ApplicationContext context: Context,
//    workerParams: WorkerParameters,
//    private val getCallsUseCase: GetCallsUseCase // Inject your use case
//) : CoroutineWorker(context, workerParams) {
//
//    override suspend fun doWork(): Result {
//        return try {
//            // Call your API here
//            val calls = getCallsUseCase() // Your use case to fetch data
//            // Log the data (you can use Logcat or any logging mechanism)
//            Log.d("aaaaaaaaa", "doWork:Fetched Calls: $calls ")
//            Result.success()
//        } catch (e: Exception) {
//            // Handle any exceptions
//            Log.d("aaaaaaaaaa", "doWork:Fetched Calls: Fail ")
//
//            println("Error fetching calls: ${e.message}")
//            Result.retry() // Retry if there's an error
//        }
//    }
//}
//
//fun scheduleApiWorker(context: Context) {
//    val workRequest = PeriodicWorkRequestBuilder<ApiWorker>(15, TimeUnit.MINUTES)
//        .build()
//
//    WorkManager.getInstance(context).enqueue(workRequest)
//}

//fun scheduleApiWork(context: Context) {
//    val workRequest = PeriodicWorkRequestBuilder<ApiCoroutineWorker>(15, TimeUnit.MINUTES)
//        .build()
//
//    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//        "ApiCoroutineWorker",
//        ExistingPeriodicWorkPolicy.KEEP,
//        workRequest
//    )
//}


