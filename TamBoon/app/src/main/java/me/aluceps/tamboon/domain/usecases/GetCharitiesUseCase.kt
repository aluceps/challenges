package me.aluceps.tamboon.domain.usecases

import io.reactivex.Scheduler
import io.reactivex.Single
import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.domain.repositories.CharitiesRepository
import javax.inject.Inject

class GetCharitiesUseCase @Inject constructor(
    private val repository: CharitiesRepository,
    scheduler: Scheduler
) : BaseUseCase<List<Charity>, Unit>(scheduler) {
    override fun buildUseCaseObservable(params: Unit): Single<List<Charity>> =
        repository.getCharities()
}