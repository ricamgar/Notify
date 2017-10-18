package com.ricamgar.notify.base.mvp

import android.support.annotation.CallSuper
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/** Abstraction for the Presenters in a MVP pattern.  */
abstract class AbstractBasePresenter<in ViewModel>(
  private val ioThread: Scheduler,
  private val mainThread: Scheduler) {

  protected var view: BaseView? = null
  private var viewModel: ViewModel? = null

  private val disposables = CompositeDisposable()

  open fun attachToView(view: BaseView, viewModel: ViewModel?) {
    this.view = view
    this.viewModel = viewModel
  }

  /**
   * Method that control the lifecycle of the view.
   * It should be called in the view's (Activity or Fragment) onDestroy() method.
   */
  @CallSuper
  open fun destroy() {
    view = null
    viewModel = null
    disposables.clear()
  }

  /**
   * Add a subscription to a [CompositeDisposable] to be handled with the lifecycle
   *
   * @param disposable The [Disposable] to add
   */
  protected fun addDisposable(disposable: Disposable) {
    disposables.add(disposable)
  }

  protected fun <T> flowableSchedulers(): FlowableTransformer<T, T> =
    FlowableTransformer { upstream -> upstream.subscribeOn(ioThread).observeOn(mainThread) }

  protected fun <T> observableSchedulers(): ObservableTransformer<T, T> =
    ObservableTransformer { upstream -> upstream.subscribeOn(ioThread).observeOn(mainThread) }

  protected fun <T> singleSchedulers(): SingleTransformer<T, T> =
    SingleTransformer { single -> single.subscribeOn(ioThread).observeOn(mainThread) }

  protected fun completableSchedulers(): CompletableTransformer =
    CompletableTransformer { single -> single.subscribeOn(ioThread).observeOn(mainThread) }

  protected fun <T> maybeSchedulers(): MaybeTransformer<T, T> =
    MaybeTransformer { maybe -> maybe.subscribeOn(ioThread).observeOn(mainThread) }

  /**
   * Base View interface used on the MVP architecture.
   */
  interface BaseView
}
