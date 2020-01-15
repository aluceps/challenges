package me.aluceps.tamboon.presentation.charities

import jp.keita.kagurazaka.rxproperty.toReadOnlyRxProperty
import me.aluceps.tamboon.domain.entities.Charity
import me.aluceps.tamboon.domain.usecases.GetCharitiesUseCase
import me.aluceps.tamboon.presentation.common.BaseViewModel
import me.aluceps.tamboon.presentation.common.addedTo
import me.aluceps.tamboon.presentation.mappers.UseCaseObserver
import javax.inject.Inject

class CharitiesViewModel @Inject constructor(
    private val useCase: GetCharitiesUseCase
) : BaseViewModel() {

    private val usecaseObserver = UseCaseObserver<List<Charity>>()

    val processing = usecaseObserver.processing.toReadOnlyRxProperty().addedTo(disposable)
    val items = usecaseObserver.succeeded.toReadOnlyRxProperty().addedTo(disposable)

    fun fetch() {
        usecaseObserver.invokeUseCase(useCase, Unit)
    }
}