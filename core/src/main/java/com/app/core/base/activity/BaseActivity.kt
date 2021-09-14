package com.app.core.base.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.core.base.dialog.LoaderDialog
import com.app.core.base.fragment.BaseFragmentCommunicator
import kotlin.properties.Delegates

abstract class BaseActivity<DataBinding: ViewDataBinding> : AppCompatActivity(), BaseFragmentCommunicator {

    var binding: DataBinding by Delegates.notNull()
    var baseCommunicator: BaseFragmentCommunicator? = null
    private val loaderDialog by lazy { LoaderDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(getActivity(), layoutResourceId)
        binding.lifecycleOwner = getActivity()
    }

    @get:LayoutRes
    abstract val layoutResourceId: Int

    fun getActivity(): AppCompatActivity = this

    fun getContext(): Context = this

    override fun showToast(message: String?) {
        message?.let { runOnUiThread {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
        } }
    }

    override fun showLoader() {
        runOnUiThread { if (!loaderDialog.isShowing) loaderDialog.show() }
    }

    override fun hideLoader() {
        runOnUiThread { if (loaderDialog.isShowing) loaderDialog.dismiss() }
    }
}