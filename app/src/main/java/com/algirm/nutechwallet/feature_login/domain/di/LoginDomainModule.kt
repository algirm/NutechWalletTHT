package com.algirm.nutechwallet.feature_login.domain.di

import com.algirm.nutechwallet.core.domain.preferences.Preferences
import com.algirm.nutechwallet.feature_login.domain.repository.LoginRepository
import com.algirm.nutechwallet.feature_login.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LoginDomainModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCases(
        repository: LoginRepository,
        preferences: Preferences
    ): LoginUseCases {
        return LoginUseCases(
            login = Login(repository),
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword(),
            saveUserInfo = SaveUserInfo(preferences)
        )
    }
}