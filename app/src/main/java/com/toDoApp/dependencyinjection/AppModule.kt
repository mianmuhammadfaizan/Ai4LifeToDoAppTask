package com.toDoApp.dependencyinjection


import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.toDoApp.network.ApiService
import com.toDoApp.storage.database.AppDatabase
import com.toDoApp.storage.database.DeleteSellUseCase
import com.toDoApp.storage.database.GetAllSellsUseCase
import com.toDoApp.storage.database.InsertSellUseCase
import com.toDoApp.storage.database.SellDao
import com.toDoApp.storage.database.SellRepository
import com.toDoApp.storage.database.UpdateSellUseCase
import com.toDoApp.viewmodels.CallRepository
import com.toDoApp.viewmodels.CallRepositoryImpl
import com.toDoApp.viewmodels.GetCallsUseCase
import com.toDoApp.viewmodels.GetProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext appContext: Context): ConnectivityManager =
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    private const val BASE_URL = "https://my-json-server.typicode.com/imkhan334/demo-1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideCallRepository(apiService: ApiService): CallRepository {
        return CallRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetCallsUseCase(callRepository: CallRepository): GetCallsUseCase {
        return GetCallsUseCase(callRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductUseCase(callRepository: CallRepository): GetProductUseCase {
        return GetProductUseCase(callRepository)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database.db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideSellDao(appDatabase: AppDatabase): SellDao {
        return appDatabase.sellDao()
    }

    @Provides
    @Singleton
    fun provideSellRepository(sellDao: SellDao): SellRepository {
        return SellRepository(sellDao)
    }

    @Provides
    @Singleton
    fun provideInsertSellUseCase(sellRepository: SellRepository): InsertSellUseCase {
        return InsertSellUseCase(sellRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateSellUseCase(sellRepository: SellRepository): UpdateSellUseCase {
        return UpdateSellUseCase(sellRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSellUseCase(sellRepository: SellRepository): DeleteSellUseCase {
        return DeleteSellUseCase(sellRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllSellsUseCase(sellRepository: SellRepository): GetAllSellsUseCase {
        return GetAllSellsUseCase(sellRepository)
    }


}





