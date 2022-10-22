package com.algirm.nutechwallet.feature_ewallet.domain.use_case

import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.domain.repository.LocalRepository

class GetUsers(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(): List<User> {
        return localRepository.getUsers()
    }
}