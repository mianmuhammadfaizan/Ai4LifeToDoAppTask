package com.toDoApp.network

import com.toDoApp.models.CallDataModel
import com.toDoApp.models.ProductDataModel
import jakarta.inject.Inject






class GetCallsUseCase @Inject constructor(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke(): List<CallDataModel> {
        return callRepository.getCalls()
    }
}

class GetProductUseCase @Inject constructor(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke(): List<ProductDataModel> {
        return callRepository.getProductList()
    }
}
