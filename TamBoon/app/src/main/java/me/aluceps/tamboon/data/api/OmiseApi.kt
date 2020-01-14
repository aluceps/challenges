package me.aluceps.tamboon.data.api

import io.reactivex.Single
import me.aluceps.tamboon.data.entities.Charity
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface OmiseApi {

    @GET("charities")
    fun getChariteis(): Single<List<Charity>>
}

class OmiseApiClient(private val core: OmiseApi) : OmiseApi by core