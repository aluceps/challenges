package me.aluceps.tamboon.domain.repositories

import io.reactivex.Single
import me.aluceps.tamboon.domain.entities.Charity

interface CharitiesRepository {
    fun getCharities(): Single<List<Charity>>
}