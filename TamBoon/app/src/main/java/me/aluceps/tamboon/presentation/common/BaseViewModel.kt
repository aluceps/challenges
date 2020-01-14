package me.aluceps.tamboon.presentation.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

abstract class BaseViewModel : ViewModel(), LifecycleOwner {
    private val lifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    override fun getLifecycle() = lifecycleRegistry

    init {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    protected val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }
}

fun <T : Disposable> T.addedTo(disposables: CompositeDisposable) = this.apply { addTo(disposables) }
