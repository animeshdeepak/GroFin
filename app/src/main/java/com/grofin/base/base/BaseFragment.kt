package com.grofin.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {
    abstract fun getLayoutId(): Int
    abstract fun getViewModelClass(): Class<VM>
    abstract fun performTasksOnActivityCreated(savedInstanceState: Bundle?)
    abstract fun executeOnlyOnce()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: VB
    lateinit var viewModel: VM

    private var hasBeenExecuted: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::binding.isInitialized)
            return binding.root

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(
            requireActivity().viewModelStore,
            viewModelFactory
        )[getViewModelClass()]
        setObserver()
        performTasksOnActivityCreated(savedInstanceState)

        if (hasBeenExecuted)
            return
        else {
            hasBeenExecuted = !hasBeenExecuted
            executeOnlyOnce()
        }
    }

    private fun setObserver() {
        observe(viewModel.isLoading, ::loading)
        observe(viewModel.errorMessage, ::errorMessage)
    }

    private fun loading(event: SingleEvent<ApiStatus>) {
        event.contentIfNotHandled?.let {
            when (it) {
                ApiStatus.LOADING ->
                    (requireActivity() as BaseActivity).progressBarVisibility(true)

                ApiStatus.SUCCESS ->
                    (requireActivity() as BaseActivity).progressBarVisibility(false)

                ApiStatus.FAILURE ->
                    (requireActivity() as BaseActivity).progressBarVisibility(false)
            }
        }
    }

    private fun errorMessage(event: SingleEvent<String>) {
        event.contentIfNotHandled?.let {
            showToastMessage(it)
        }
    }

    fun showToastMessage(message: String?) =
        (requireActivity() as BaseActivity).showToastMessage(message)

}