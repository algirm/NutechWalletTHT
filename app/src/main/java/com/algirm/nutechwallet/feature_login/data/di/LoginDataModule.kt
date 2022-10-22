package com.algirm.nutechwallet.feature_login.data.di

import com.algirm.nutechwallet.core.util.AppConst.Companion.BASE_URL
import com.algirm.nutechwallet.feature_login.data.LoginApi
import com.algirm.nutechwallet.feature_login.data.LoginRepositoryImpl
import com.algirm.nutechwallet.feature_login.domain.repository.LoginRepository
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
object LoginDataModule {

    @Singleton
    @Provides
    fun provideLoginRepository(api: LoginApi): LoginRepository {
        return LoginRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideLoginApi(client: OkHttpClient): LoginApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }
}