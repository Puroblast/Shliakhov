package com.puroblast.tintest.di

import com.google.gson.GsonBuilder
import com.puroblast.tintest.domain.FilmsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://kinopoiskapiunofficial.tech/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFilmsApi(): FilmsApi {
        val gson = GsonBuilder()
            .create()

        val api: FilmsApi by lazy {
            Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(FilmsApi::class.java)
        }

        return api
    }
}
