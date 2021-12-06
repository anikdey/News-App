package com.app.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val showLoader by lazy { MutableLiveData<Boolean>() }
    //val snackBarMessage by lazy { SingleLiveEvent<SnackBarMessage>() }
    val toastMessage by lazy { MutableLiveData<String>() }

}