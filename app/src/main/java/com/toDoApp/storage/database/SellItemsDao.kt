package com.toDoApp.storage.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.toDoApp.models.Sell
import kotlinx.coroutines.flow.Flow


@Dao
interface SellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sell: Sell)

    @Update
    suspend fun update(sell: Sell)

    @Delete
    suspend fun delete(sell: Sell)

    @Query("SELECT * FROM sell_table")
    fun getAllSells(): Flow<List<Sell>>
}



@Database(entities = [Sell::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sellDao(): SellDao
}