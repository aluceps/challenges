package me.aluceps.tamboon.domain.usecases

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import timber.log.Timber

abstract class BaseUseCase<ReturnType, Params>(val scheduler: Scheduler) {
    protected abstract fun buildUseCaseObservable(params: Params): Single<ReturnType>
    private val disposables = CompositeDisposable()
    private val executionDisposables = CompositeDisposable()

    fun execute(disposableObserver: DisposableSingleObserver<ReturnType>, params: Params) {
        addDisposable(this.buildUseCaseObservable(params)
            .doOnError { Timber.e(it) }
            .doFinally { executionDisposables.clear() }
            .subscribeOn(scheduler)
            .subscribeWith(disposableObserver))
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
        if (!executionDisposables.isDisposed) {
            executionDisposables.dispose()
        }
    }

    protected fun addExecutionDisposable(disposable: Disposable) {
        executionDisposables.add(disposable)
    }

    protected fun <TSubReturnType, TSubParam> invokeUseCase(
        useCase: BaseUseCase<TSubReturnType, TSubParam>,
        subParam: TSubParam
    ): Single<TSubReturnType> {
        return Single.create<TSubReturnType> { emitter ->
            val observer = object : DisposableSingleObserver<TSubReturnType>() {
                override fun onSuccess(t: TSubReturnType) {
                    emitter.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    emitter.onError(e)
                }
            }
            useCase.execute(observer, subParam)
        }
    }
}
