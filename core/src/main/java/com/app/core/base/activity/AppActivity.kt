package com.app.core.base.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.app.core.base.viewmodel.BaseViewModel

abstract class AppActivity<ViewModel: BaseViewModel, DataBinding: ViewDataBinding>: BaseActivity<DataBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel(binding)
    }

    abstract fun setViewModel(binding: DataBinding)

}