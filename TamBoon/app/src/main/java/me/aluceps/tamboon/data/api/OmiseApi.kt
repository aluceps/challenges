package me.aluceps.tamboon.data.api

import io.reactivex.Completable
import io.reactivex.Single
import me.aluceps.tamboon.data.entities.Charity
import me.aluceps.tamboon.data.entities.Donation
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface OmiseApi {

    @GET("charities")
    fun getChariteis(): Single<List<Charity>>

    @POST("donations")
    fun donations(@Body donation: Donation): Completable
}

class OmiseApiClient(private val core: OmiseApi) : OmiseApi by core