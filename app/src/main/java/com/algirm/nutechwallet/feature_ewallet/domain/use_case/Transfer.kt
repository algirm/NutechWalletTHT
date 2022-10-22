package com.algirm.nutechwallet.feature_ewallet.domain.use_case

import com.algirm.nutechwallet.feature_ewallet.data.local.entity.Transaction
import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository
import com.algirm.nutechwallet.feature_ewallet.domain.repository.LocalRepository
import java.util.*

class Transfer(
    private val ewalletRepository: EwalletRepository,
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(amount: Int, userId: Int): Result<String> {
        val result = ewalletRepository.transfer(amount)

        return if (result.isSuccess) {
            localRepository.addTransaction(
                Transaction(
                    userId = userId,
                    amount = amount.toString(),
                    date = Date()
                )
            )
            Result.success(result.getOrNull()?: "Transfer Berhasil")
        } else {

            Result.failure(result.exceptionOrNull()?: Exception())
        }
    }
}