package me.aluceps.tamboon.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import me.aluceps.tamboon.R
import me.aluceps.tamboon.data.api.OmiseApi
import me.aluceps.tamboon.data.api.OmiseApiClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
internal object AppModule {

    @Singleton @Provides @JvmStatic
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton @Provides @JvmStatic
    fun provideRetrofit(application: Application, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://${application.resources.getString(R.string.api_host)}/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Singleton @Provides @JvmStatic
    fun provideOmiseApi(retrofit: Retrofit): OmiseApi =
        OmiseApiClient(retrofit.create(OmiseApi::class.java))
}