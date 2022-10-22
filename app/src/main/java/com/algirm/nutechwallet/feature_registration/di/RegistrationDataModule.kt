package com.algirm.nutechwallet.feature_registration.di

import com.algirm.nutechwallet.core.util.AppConst
import com.algirm.nutechwallet.feature_login.data.LoginApi
import com.algirm.nutechwallet.feature_registration.data.RegisterApi
import com.algirm.nutechwallet.feature_registration.data.repository.RegisterRepositoryImpl
import com.algirm.nutechwallet.feature_registration.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationDataModule {

    @Singleton
    @Provides
    fun provideRegisterRepository(api: RegisterApi): RegisterRepository {
        return RegisterRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideRegisterApi(
        okHttpClient: OkHttpClient
    ): RegisterApi {
        return Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterApi::class.java)
    }
}