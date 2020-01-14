package me.aluceps.tamboon.data.repositories

import io.reactivex.Single
import me.aluceps.tamboon.data.api.OmiseApi
import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.domain.repositories.CharitiesRepository
import javax.inject.Inject

class CharitiesRepositoryImpl @Inject constructor(
    private val api: OmiseApi
) : CharitiesRepository {
    override fun getCharities(): Single<List<Charity>> {
        return api.getChariteis()
            .map { items ->
                items.map { it.toEntity() }
            }
    }
}