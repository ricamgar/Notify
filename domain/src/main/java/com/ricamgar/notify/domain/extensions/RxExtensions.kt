@file:Suppress("unused")

package com.ricamgar.notify.domain.extensions

import io.reactivex.*
import java.util.concurrent.Callable
import java.util.concurrent.Future

fun <T> Single<T>.doCompletableOnSuccess(function: (T) -> Completable): Single<T> =
  flatMap { function(it).andThen(Single.just(it)) }

fun <T> Single<T>.doCompletableOnSuccessIf(predicate: (T) -> Boolean, function: (T) -> Completable): Single<T> =
  flatMap { if (predicate(it)) function(it).andThen(Single.just(it)) else Single.just(it) }

fun <T> Observable<T>.doCompletableOnNext(function: (T) -> Completable): Observable<T> =
  flatMap { function(it).andThen(Observable.just(it)) }

fun <T> Maybe<T>.doCompletableOnSuccess(function: (T) -> Completable): Maybe<T> =
  flatMap { function(it).andThen(Maybe.just(it)) }

fun Completable.resumeCompletableOnError(function: (Throwable) -> Completable): Completable =
  onErrorResumeNext { error ->
    function(error).andThen(Completable.error(error))
  }

fun <T> Single<T>.resumeCompletableOnError(function: (Throwable) -> Completable): Single<T> =
  onErrorResumeNext { error ->
    function(error).andThen(Single.error(error))
  }

fun <T> Single<List<T>>.flatMapIterable(): Observable<T> = toObservable().flatMapIterable { items -> items }

fun <T> Maybe<List<T>>.flatMapIterable(): Observable<T> = toObservable().flatMapIterable { items -> items }

fun <T> Maybe<T>.deferDefaultIfEmpty(function: () -> T): Maybe<T> = switchIfEmpty(Maybe.fromCallable(function))

fun <T : Any> Future<T>.toMaybe(): Maybe<T> = Maybe.fromFuture(this)
fun <T : Any> Callable<T>.toMaybe(): Maybe<T> = Maybe.fromCallable(this)
fun <T : Any> (() -> T).toMaybe(): Maybe<T> = Maybe.fromCallable(this)
fun <T : Any> T?.toMaybe(): Maybe<T> = Maybe.create { s ->
  if (this != null) s.onSuccess(this)
  s.onComplete()
}

fun <T : Any> T.toSingle(): Single<T> = Single.just(this)
fun <T : Any> (() -> T).toSingle(): Single<T> = Single.fromCallable(this)

fun <T : Any> T.toObservable(): Observable<T> = Observable.just(this)
fun <T : Any> (() -> T).toObservable(): Observable<T> = Observable.fromCallable(this)

infix fun <T> MaybeSource<out T>.concat(other: MaybeSource<out T>): Flowable<T> =
  Maybe.concat(this, other)

infix fun <T> MaybeSource<out T>.merge(other: MaybeSource<out T>): Flowable<T> =
  Maybe.merge(this, other)

infix fun <T> SingleSource<out T>.concat(other: SingleSource<out T>): Flowable<T> =
  Single.concat(this, other)

infix fun <T> SingleSource<out T>.merge(other: SingleSource<out T>): Flowable<T> =
  Single.merge(this, other)

infix fun <T> ObservableSource<out T>.concat(other: ObservableSource<out T>): Observable<T> =
  Observable.concat(this, other)

infix fun <T> ObservableSource<out T>.merge(other: ObservableSource<out T>): Observable<T> =
  Observable.merge(this, other)