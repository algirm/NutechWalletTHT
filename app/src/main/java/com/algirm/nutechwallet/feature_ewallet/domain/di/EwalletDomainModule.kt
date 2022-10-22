package com.algirm.nutechwallet.feature_ewallet.domain.di

import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository
import com.algirm.nutechwallet.feature_ewallet.domain.repository.LocalRepository
import com.algirm.nutechwallet.feature_ewallet.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object EwalletDomainModule {

    @ViewModelScoped
    @Provides
    fun provideEwalletUseCases(
        ewalletRepository: EwalletRepository,
        localRepository: LocalRepository
    ): EwalletUseCases {
        return EwalletUseCases(
            getBalance = GetBalance(ewalletRepository),
            topUp = TopUp(ewalletRepository),
            transfer = Transfer(ewalletRepository, localRepository),
            getTransactionHistory = GetTransactionHistory(ewalletRepository),
            getUsers = GetUsers(localRepository),
            addUser = AddUser(localRepository),
            deleteUser = DeleteUser(localRepository)
        )
    }
}