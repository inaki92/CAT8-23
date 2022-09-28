package com.example.graphqlspaceexample.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.graphqlspaceexample.network.SpaceRepository
import com.example.graphqlspaceexample.network.SpaceRepositoryImpl
import com.example.graphqlspaceexample.viewmodel.SpaceViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private var BASE_URL = "http://api.spacex.land/graphql/"

val networkModule = module {
    single { HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    } }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(get())
            .build()
    }
}

val viewModelModule = module {
    viewModel { SpaceViewModel(get<SpaceRepositoryImpl>()) }
}

val repositoryModule = module {
    single { SpaceRepositoryImpl(get()) }
}