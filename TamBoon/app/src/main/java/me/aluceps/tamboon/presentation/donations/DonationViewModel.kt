package me.aluceps.tamboon.presentation.donations

import jp.keita.kagurazaka.rxproperty.RxProperty
import jp.keita.kagurazaka.rxproperty.toReadOnlyRxProperty
import me.aluceps.tamboon.domain.entities.*
import me.aluceps.tamboon.presentation.common.BaseViewModel
import me.aluceps.tamboon.presentation.common.addedTo
import javax.inject.Inject

class DonationViewModel @Inject constructor() : BaseViewModel() {

    val name = RxProperty<String>()
    val number = RxProperty<String>()
    val dateMonth = RxProperty<String>()
    val dateYear = RxProperty<String>()
    val cvc = RxProperty<String>()

    private val donorName = name.map { DonationName(it) }.toReadOnlyRxProperty().addedTo(disposable)
    private val donorNumber = number.map { DonationNumber(it) }.toReadOnlyRxProperty().addedTo(disposable)
    private val donorDateMonth = dateMonth.map { DonationDateMonth(it) }.toReadOnlyRxProperty().addedTo(disposable)
    private val donorDateYear = dateYear.map { DonationDateYear(it) }.toReadOnlyRxProperty().addedTo(disposable)
    private val donorCVC = cvc.map { DonationCVC(it) }.toReadOnlyRxProperty().addedTo(disposable)
}