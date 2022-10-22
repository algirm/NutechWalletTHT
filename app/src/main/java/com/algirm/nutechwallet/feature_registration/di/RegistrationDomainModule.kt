package com.algirm.nutechwallet.feature_registration.di

import com.algirm.nutechwallet.feature_login.domain.use_case.ValidateEmail
import com.algirm.nutechwallet.feature_login.domain.use_case.ValidatePassword
import com.algirm.nutechwallet.feature_registration.domain.repository.RegisterRepository
import com.algirm.nutechwallet.feature_registration.domain.use_case.Register
import com.algirm.nutechwallet.feature_registration.domain.use_case.RegisterUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RegistrationDomainModule {

    @ViewModelScoped
    @Provides
    fun provideRegisterUseCases(
        repository: RegisterRepository
    ): RegisterUseCases {
        return RegisterUseCases(
            register = Register(repository),
            validateEmail = ValidateEmail(),
            validatePassword = ValidatePassword()
        )
    }
}