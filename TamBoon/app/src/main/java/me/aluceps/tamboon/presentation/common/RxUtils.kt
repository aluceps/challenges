package me.aluceps.tamboon.presentation.common

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class BindableCommand(private val func: () -> Unit) : Disposable {
    private val subject = PublishSubject.create<Unit>()
    private val subscriber = subject
            .throttleFirst(100, TimeUnit.MILLISECONDS)
            .subscribe {
                func.invoke()
            }

    override fun isDisposed(): Boolean = subscriber.isDisposed

    override fun dispose() {
        subscriber.dispose()
    }

    fun invoke() {
        subject.onNext(Unit)
    }
}