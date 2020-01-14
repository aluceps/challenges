package me.aluceps.tamboon.presentation.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    fun addDisposable(disposable: CompositeDisposable) {
        disposable.add(disposable)
    }

    override fun onCleared() {
        disposable.clear()
    }
}