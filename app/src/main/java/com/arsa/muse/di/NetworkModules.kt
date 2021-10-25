package com.arsa.muse.di

import android.provider.UserDictionary.Words.APP_ID
import com.arsa.muse.repository.NetworkInterface
import com.arsa.muse.utlity.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient:OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    @Singleton
    @Provides
    fun provideNetwork(retrofit:Retrofit): NetworkInterface = retrofit.create(NetworkInterface::class.java)

    @Singleton
    @Provides
    fun provideAuthInterceptorOkHttpClient():OkHttpClient{
       return OkHttpClient().newBuilder().addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader(Constants.apiKeyHeader, Constants.apiKey)
                .build()
            chain.proceed(request)
        }.build()
    }








}