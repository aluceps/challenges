package me.aluceps.tamboon.presentation.mappers

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import me.aluceps.tamboon.domain.usecases.BaseUseCase

class UseCaseMappableObserver<SourceType, ReturnType> {
    private val _processing = BehaviorSubject.create<Boolean>()
    val processing: Observable<Boolean> = _processing
    private val _succeeded = PublishSubject.create<ReturnType>()
    val succeeded: Observable<ReturnType> = _succeeded
    private val _failed = PublishSubject.create<Throwable>()
    val failed: Observable<Throwable> = _failed

    fun singleObserverIfNotPerformed(mapper: (SourceType) -> ReturnType): DisposableSingleObserver<SourceType>? {
        if (_processing.value == true) {
            _failed.onNext(Throwable())
            return null
        }
        _processing.onNext(true)

        return object : DisposableSingleObserver<SourceType>() {
            override fun onSuccess(t: SourceType) {
                _succeeded.onNext(mapper(t))
                _processing.onNext(false)
            }

            override fun onError(e: Throwable) {
                _failed.onNext(e)
                _processing.onNext(false)
            }
        }
    }

    fun singleObserver(mapper: (SourceType) -> ReturnType): DisposableSingleObserver<SourceType> {
        return object : DisposableSingleObserver<SourceType>() {
            override fun onSuccess(t: SourceType) {
                _succeeded.onNext(mapper(t))
            }

            override fun onError(e: Throwable) {
                _failed.onNext(e)
            }
        }
    }

    fun observableObserver(mapper: (SourceType) -> ReturnType): DisposableObserver<SourceType> {
        return object : DisposableObserver<SourceType>() {
            override fun onComplete() {
            }

            override fun onNext(t: SourceType) {
                _succeeded.onNext(mapper(t))
            }

            override fun onError(e: Throwable) {
                _failed.onNext(e)
            }
        }
    }
}

class UseCaseObserver<T> {
    private val core: UseCaseMappableObserver<T, T> = UseCaseMappableObserver()
    val processing: Observable<Boolean> = core.processing
    val succeeded: Observable<T> = core.succeeded
    val failed: Observable<Throwable> = core.failed

    fun singleObserverIfNotPerformed(): DisposableSingleObserver<T>? =
        core.singleObserverIfNotPerformed { it -> it }

    fun singleObserver(): DisposableSingleObserver<T>? =
        core.singleObserver { it -> it }

    // singleObserver~~~があまりに冗長なのでつくった
    fun <TArg> invokeUseCase(useCase: BaseUseCase<T, TArg>, params: TArg) {
        singleObserverIfNotPerformed()?.let { observer ->
            useCase.execute(observer, params)
        }
    }
}
