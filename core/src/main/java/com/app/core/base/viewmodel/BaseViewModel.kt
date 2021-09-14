package com.app.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val showLoader by lazy { MutableLiveData<Boolean>() }
    //val snackBarMessage by lazy { SingleLiveEvent<SnackBarMessage>() }
    val toastMessage by lazy { MutableLiveData<String>() }

    val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


//    protected fun checkNetwork(context: Context): Boolean {
//        var isNetworkAvailable = isNetworkAvailable(context)
//        if(!isNetworkAvailable){
//            toastMessage.postValue(context.getString(R.string.check_internet_connection))
//        }
//        return isNetworkAvailable
//    }

    protected fun handleError(throwable: Throwable) {
        //snackBarMessage.value = SnackBarMessage(throwable.message)
    }


}