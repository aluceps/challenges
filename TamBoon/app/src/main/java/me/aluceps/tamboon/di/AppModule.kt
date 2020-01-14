package me.aluceps.tamboon.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import me.aluceps.tamboon.R
import me.aluceps.tamboon.data.api.OmiseApi
import me.aluceps.tamboon.data.api.OmiseApiClient
import me.aluceps.tamboon.data.repositories.CharitiesRepositoryImpl
import me.aluceps.tamboon.domain.repositories.CharitiesRepository
import me.aluceps.tamboon.domain.usecases.GetCharitiesUseCase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton @Provides @JvmStatic
    fun provideOmiseApi(retrofit: Retrofit): OmiseApi =
        OmiseApiClient(retrofit.create(OmiseApi::class.java))

    @Singleton @Provides @JvmStatic
    fun provideCharitiesRepository(api: OmiseApi): CharitiesRepository =
        CharitiesRepositoryImpl(api)

    @Singleton @Provides @JvmStatic
    fun provideGetCharitiesUseCase(repository: CharitiesRepository): GetCharitiesUseCase =
        GetCharitiesUseCase(repository, Schedulers.io())
}