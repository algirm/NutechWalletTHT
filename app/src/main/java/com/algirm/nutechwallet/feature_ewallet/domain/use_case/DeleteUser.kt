package com.algirm.nutechwallet.feature_ewallet.domain.use_case

import com.algirm.nutechwallet.feature_ewallet.data.local.entity.User
import com.algirm.nutechwallet.feature_ewallet.domain.repository.LocalRepository

class DeleteUser(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(user: User){
        return localRepository.deleteUser(user)
    }
}