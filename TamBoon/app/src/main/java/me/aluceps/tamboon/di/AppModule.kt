package me.aluceps.tamboon.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import me.aluceps.tamboon.R
import me.aluceps.tamboon.data.api.LocalOmiseApi
import me.aluceps.tamboon.data.api.LocalOmiseApiClient
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
    fun provideLocalOmiseApi(application: Application, moshi: Moshi): LocalOmiseApi =
        Retrofit.Builder()
            .baseUrl("http://${application.resources.getString(R.string.api_local_host)}/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().let { retrofit ->
                LocalOmiseApiClient(retrofit.create(LocalOmiseApi::class.java))
            }

//    @Singleton @Provides @JvmStatic
//    fun provideRemoteOmiseApi(application: Application, moshi: Moshi): LocalOmiseApi =
//            Retrofit.Builder()
//                    .baseUrl("http://${application.resources.getString(R.string.api_remote_host)}/")
//                    .addConverterFactory(MoshiConverterFactory.create(moshi))
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build().let { retrofit ->
//                        LocalOmiseApiClient(retrofit.create(LocalOmiseApi::class.java))
//                    }

    @Singleton @Provides @JvmStatic
    fun provideCharitiesRepository(api: LocalOmiseApi): CharitiesRepository =
        CharitiesRepositoryImpl(api)

    @Singleton @Provides @JvmStatic
    fun provideGetCharitiesUseCase(repository: CharitiesRepository): GetCharitiesUseCase =
        GetCharitiesUseCase(repository, Schedulers.io())
}