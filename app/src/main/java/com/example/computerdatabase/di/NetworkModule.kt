package com.example.computerdatabase.di

import com.example.computerdatabase.BuildConfig
import com.example.computerdatabase.api.NetworkService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClientBuilder.addInterceptor(interceptor)
        }

        val httpClient = httpClientBuilder
            .readTimeout(BuildConfig.HTTP_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(BuildConfig.HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient)
            .build()
    }
}