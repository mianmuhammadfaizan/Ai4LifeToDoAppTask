package com.toDoApp.storage.database

import com.toDoApp.models.Sell
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Singleton
class SellRepository @Inject constructor(
    private val sellDao: SellDao
) {
    fun getAllSells(): Flow<List<Sell>> = sellDao.getAllSells()

    suspend fun insert(sell: Sell) {
        sellDao.insert(sell)
    }

    suspend fun update(sell: Sell) {
        sellDao.update(sell)
    }

    suspend fun delete(sell: Sell) {
        sellDao.delete(sell)
    }
}