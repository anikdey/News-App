package com.app.core.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.app.core.base.viewmodel.BaseViewModel

abstract class AppFragment<ViewModel : BaseViewModel, DataBinding : ViewDataBinding> : BaseFragment<DataBinding>() {

    //var viewModel: ViewModel by Delegates.notNull()

    //abstract fun createViewModel(): ViewModel

    abstract fun setViewModel(binding: DataBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel(dataBinding)
    }
}