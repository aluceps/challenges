package me.aluceps.tamboon.data.api

import io.reactivex.Completable
import io.reactivex.Single
import me.aluceps.tamboon.data.responses.Charity
import me.aluceps.tamboon.data.responses.Donation
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface LocalOmiseApi {

    @GET("charities")
    fun getChariteis(): Single<List<Charity>>

    @POST("donations")
    fun donations(@Body donation: Donation): Completable
}

class LocalOmiseApiClient(private val core: LocalOmiseApi) : LocalOmiseApi by core

//@Singleton
//interface RemoteOmiseApi {
//}
//
//class RemoteOmiseApiClient(private val core: RemoteOmiseApi) : RemoteOmiseApi by core