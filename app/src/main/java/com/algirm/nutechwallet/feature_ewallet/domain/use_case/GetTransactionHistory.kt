package com.algirm.nutechwallet.feature_ewallet.domain.use_case

import com.algirm.nutechwallet.feature_ewallet.data.remote.dto.TransactionDto
import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository

class GetTransactionHistory(private val ewalletRepository: EwalletRepository) {

    suspend operator fun invoke(): Result<List<TransactionDto>> {
        return ewalletRepository.getTransactionHistory()
    }
}