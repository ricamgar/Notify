package com.ricamgar.notify.base.mvp;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;

import java.io.Serializable;

@SuppressWarnings("unchecked")
public abstract class ViewDelegate<ViewModel, Presenter
        extends AbstractBasePresenter<ViewModel>> {

    Presenter presenter;
    ViewModel viewModel;

    private String presentationModelKey;

    public void onCreate(AbstractBasePresenter.BaseView baseView, @Nullable Bundle savedInstanceState) {
        presentationModelKey = baseView.getClass().getCanonicalName() + "$PresentationModel";
        presenter = initializePresenter();
        viewModel = restoreViewModel(savedInstanceState);
        if (viewModel == null) {
            viewModel = createViewModel();
        }
    }

    public void onStart(AbstractBasePresenter.BaseView baseView) {
        presenter.attachToView(baseView, viewModel);
    }

    @Nullable
    private ViewModel restoreViewModel(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Object potentialPresentationModel = savedInstanceState.get(presentationModelKey);
            try {
                return (ViewModel) potentialPresentationModel;
            } catch (ClassCastException ex) {
                throw new IllegalStateException(String.format(
                      "We expected a presentationModel saved in the bundle under the key: \"%s\", but was: %s",
                      presentationModelKey, potentialPresentationModel.toString()));
            }
        }
        return null;
    }

    public void onSavedInstanceState(Bundle outState) {
        if (viewModel instanceof Parcelable) {
            outState.putParcelable(presentationModelKey, (Parcelable) viewModel);
//        } else if (viewModel instanceof ArrayList) {
//            // TODO: 22/07/16 check this case
        } else if (viewModel instanceof Serializable) {
            outState.putSerializable(presentationModelKey, (Serializable) viewModel);
        } else {
            throw new IllegalArgumentException(
                  "Your presentation model must either implement Parcelable or Serializable interface: "
                        + viewModel.getClass().getCanonicalName());
        }
    }

    public void onDestroy() {
        presenter.destroy();
    }

    protected abstract Presenter initializePresenter();

    protected abstract ViewModel createViewModel();
}
