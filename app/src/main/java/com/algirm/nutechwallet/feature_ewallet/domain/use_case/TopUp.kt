package com.algirm.nutechwallet.feature_ewallet.domain.use_case

import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository

class TopUp(private val ewalletRepository: EwalletRepository) {

    suspend operator fun invoke(amount: Int): Result<String> {
        return ewalletRepository.topUp(amount)
    }
}