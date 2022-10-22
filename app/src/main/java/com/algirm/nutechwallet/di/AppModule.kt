package com.algirm.nutechwallet.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.algirm.nutechwallet.core.auth.AuthInterceptorImpl
import com.algirm.nutechwallet.core.auth.SessionManager
import com.algirm.nutechwallet.core.data.DefaultPreferences
import com.algirm.nutechwallet.core.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferences(
        sharedPreferences: SharedPreferences
    ): Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideSessionManager(
        sharedPreferences: Preferences
    ): SessionManager = SessionManager(
        preferences = sharedPreferences
    )

    @Singleton
    @Provides
    fun provideAuthInterceptorImpl(
        sessionManager: SessionManager
    ): AuthInterceptorImpl = AuthInterceptorImpl(sessionManager = sessionManager)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptorImpl
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

}