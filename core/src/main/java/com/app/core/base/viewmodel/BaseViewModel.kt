package com.app.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import com.anik.network.util.Resource

open class BaseViewModel : ViewModel() {

    val showLoader by lazy { MutableLiveData<Boolean>() }
    //val snackBarMessage by lazy { SingleLiveEvent<SnackBarMessage>() }
    val toastMessage by lazy { MutableLiveData<String>() }

    val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun<T> handleError(response: Resource<T>) {
        when(response) {
            is Resource.NetworkError -> { toastMessage.value = response.message}
            is Resource.GenericError -> { toastMessage.value = "${response.error?.message}" }
            else -> {

            }
        }
    }


}