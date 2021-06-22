package com.grofin.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.grofin.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {
    abstract fun getLayoutId(): Int
    abstract fun getViewModelClass(): Class<VM>
    abstract fun performTasksOnActivityCreated(savedInstanceState: Bundle?)
    abstract fun executeOnlyOnce()
    abstract fun setObserver()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: VB
    private lateinit var viewModel: VM

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

        if (hasBeenExecuted) {
            return
        } else {
            hasBeenExecuted = !hasBeenExecuted
            executeOnlyOnce()
        }
    }

    fun showToastMessage(message: String?) {
        if (message != null)
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(requireContext(), getString(R.string.no_msg_found), Toast.LENGTH_SHORT).show()
    }
}