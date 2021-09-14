package com.app.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.Delegates

abstract class BaseFragment<DataBinding : ViewDataBinding> : Fragment() {

    var binding: DataBinding by Delegates.notNull()

    var baseCommunicator: BaseFragmentCommunicator? = null

    @get:LayoutRes
    abstract val layoutResourceId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseFragmentCommunicator) {
            baseCommunicator = context
        } else {
            throw ClassCastException("$context must implement BaseFragmentCommunicator")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    protected abstract fun init()

    fun getFragment(): Fragment = this
}
