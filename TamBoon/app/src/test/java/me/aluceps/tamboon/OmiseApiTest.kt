package me.aluceps.tamboon

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import me.aluceps.tamboon.data.api.OmiseApi
import me.aluceps.tamboon.data.api.OmiseApiClient
import me.aluceps.tamboon.data.responses.Donation
import org.junit.Ignore
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class OmiseApiTest {

    private fun createMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private fun createRetrofit(): Retrofit =
        createMoshi().let { moshi ->
            Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

    @Test
    @Ignore("for development")
    fun getCharities_should_success() {
        val retrofit = createRetrofit()
        val api = retrofit.create(OmiseApi::class.java)

        OmiseApiClient(api).getChariteis()
            .test()
            .await()
            .assertNoErrors()
            .assertValue { it.any() }
    }


    @Test
    @Ignore("for development")
    fun donation_should_success() {
        val retrofit = createRetrofit()
        val api = retrofit.create(OmiseApi::class.java)

        OmiseApiClient(api).donations(Donation("John Smith", "tokn_test_5ik016vyuyo68kuvhlu", 1000))
            .test()
            .await()
            .assertNoErrors()
            .assertNoValues()
    }
}