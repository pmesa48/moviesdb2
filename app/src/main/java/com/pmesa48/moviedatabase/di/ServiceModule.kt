package com.pmesa48.moviedatabase.di

import com.pmesa48.moviedatabase.BuildConfig
import com.pmesa48.moviedatabase.data.local.DiscoverCache
import com.pmesa48.moviedatabase.data.local.DiscoverCacheRoom
import com.pmesa48.moviedatabase.data.api.discover.DiscoverService
import com.pmesa48.moviedatabase.data.api.discover.DiscoverServiceRetrofit
import com.pmesa48.moviedatabase.data.api.AuthInterceptor
import com.pmesa48.moviedatabase.data.local.DiscoverDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
open class ServiceModule {


    protected open fun baseUrl() = BuildConfig.API_BASE_URL

    @Provides
    fun provideRetrofit(paramInterceptor: AuthInterceptor, loggingInterceptor: HttpLoggingInterceptor) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .client(OkHttpClient()
                .newBuilder()
                .addInterceptor(paramInterceptor)
                .addInterceptor(loggingInterceptor)
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideParamInterceptor() : AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    fun provideDiscoverService(retrofit: Retrofit) : DiscoverService {
        return DiscoverServiceRetrofit(retrofit)
    }

    @Provides
    fun providesDiscoverCache(discoverDao: DiscoverDao): DiscoverCache {
        return DiscoverCacheRoom(discoverDao)
    }
}