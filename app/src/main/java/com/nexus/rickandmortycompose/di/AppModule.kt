package com.nexus.rickandmortycompose.di

import com.nexus.rickandmortycompose.network.services.CharactersService
import com.nexus.rickandmortycompose.network.services.EpisodesService
import com.nexus.rickandmortycompose.network.services.LocationsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()


        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersService(retrofit: Retrofit): CharactersService {
        return retrofit.create(CharactersService::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodesService(retrofit: Retrofit) : EpisodesService {
        return retrofit.create(EpisodesService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationsService(retrofit: Retrofit) : LocationsService {
        return retrofit.create(LocationsService::class.java)
    }
}