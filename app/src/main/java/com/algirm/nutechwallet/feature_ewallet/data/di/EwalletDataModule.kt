package com.algirm.nutechwallet.feature_ewallet.data.di

import android.app.Application
import androidx.room.Room
import com.algirm.nutechwallet.core.util.AppConst
import com.algirm.nutechwallet.feature_ewallet.data.local.EwalletDatabase
import com.algirm.nutechwallet.feature_ewallet.data.remote.EwalletApi
import com.algirm.nutechwallet.feature_ewallet.data.repository.EwalletRepositoryImpl
import com.algirm.nutechwallet.feature_ewallet.data.repository.LocalRepositoryImpl
import com.algirm.nutechwallet.feature_ewallet.domain.repository.EwalletRepository
import com.algirm.nutechwallet.feature_ewallet.domain.repository.LocalRepository
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
object EwalletDataModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDb: EwalletDatabase
    ): LocalRepository {
        return LocalRepositoryImpl(userDb)
    }

    @Singleton
    @Provides
    fun provideEwalletRepository(
        api: EwalletApi,
        userDb: EwalletDatabase
    ): EwalletRepository {
        return EwalletRepositoryImpl(api, userDb)
    }

    @Singleton
    @Provides
    fun provideEwalletApi(client: OkHttpClient): EwalletApi {
        return Retrofit.Builder()
            .baseUrl(AppConst.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EwalletApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application): EwalletDatabase {
        return Room.databaseBuilder(
            app,
            EwalletDatabase::class.java,
            "user_db"
        ).build()
    }
}