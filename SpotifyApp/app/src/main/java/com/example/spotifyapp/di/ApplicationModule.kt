package com.example.spotifyapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.spotifyapp.model.authentication.TokenHandling
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Named("masterKeyAlias")
    fun providesMasterKeyAlias(): String =
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    @Provides
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context,
        @Named("masterKeyAlias") masterKeyAlias: String
    ): SharedPreferences =
        EncryptedSharedPreferences.create(
            "spt-app",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Provides
    fun providesTokenHandling(
        sharedPreferences: SharedPreferences
    ): TokenHandling =
        TokenHandling(sharedPreferences)
}