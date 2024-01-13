package com.app.church.di

import com.app.church.data.AuthInterceptor
import com.app.church.data.ChurchApi
import com.app.church.data.repository.PostRepositoryImpl
import com.app.church.data.repository.UserPostRepositoryImpl
import com.app.church.domain.repository.PostRepository
import com.app.church.domain.repository.UserPostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTestApi(): ChurchApi {
        val authInterceptor = AuthInterceptor("")
        val okHttpClient = okhttp3.OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChurchApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(api: ChurchApi): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserPostRepository(api: ChurchApi): UserPostRepository {
        return UserPostRepositoryImpl(api)
    }
}