package com.toDoApp.storage.database

import com.toDoApp.models.Sell
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertSellUseCase @Inject constructor(
    private val sellRepository: SellRepository
) {
    suspend operator fun invoke(sell: Sell) {
        sellRepository.insert(sell)
    }
}
class UpdateSellUseCase @Inject constructor(
    private val sellRepository: SellRepository
) {
    suspend operator fun invoke(sell: Sell) {
        sellRepository.update(sell)
    }
}
class DeleteSellUseCase @Inject constructor(
    private val sellRepository: SellRepository
) {
    suspend operator fun invoke(sell: Sell) {
        sellRepository.delete(sell)
    }
}
class GetAllSellsUseCase @Inject constructor(
    private val sellRepository: SellRepository
) {
    operator fun invoke(): Flow<List<Sell>> = sellRepository.getAllSells()
}
