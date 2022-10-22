package com.algirm.nutechwallet.feature_ewallet.domain.use_case

import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository

class GetBalance(private val repository: EwalletRepository) {

    suspend operator fun invoke(): Result<Int> {
        return repository.getBalance()
    }
}