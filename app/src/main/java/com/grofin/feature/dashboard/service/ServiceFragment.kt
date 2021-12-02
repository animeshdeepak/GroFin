package com.grofin.feature.dashboard.service

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentServiceBinding
import com.grofin.feature.dashboard.service.adapter.ServiceAdapter
import com.grofin.feature.dashboard.ui.HomeFragmentDirections
import com.grofin.feature.response.CategoriesResponse
import com.grofin.feature.response.Category

class ServiceFragment : BaseFragment<FragmentServiceBinding, ServiceViewModel>() {
    private lateinit var serviceAdapter: ServiceAdapter

    companion object {
        fun newInstance(): ServiceFragment = ServiceFragment()
    }

    override fun getLayoutId() = R.layout.fragment_service

    override fun getViewModelClass() = ServiceViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initViews()
        initListener()
        setUpObserver()
    }

    override fun initViews() {
        binding.vm = viewModel
        viewModel.getCategories()
    }

    private fun setUpRecyclerView(categoriesList: ArrayList<Category>?) {
        binding.rvServices.apply {
            serviceAdapter = ServiceAdapter(categoriesList)
            setHasFixedSize(true)
            /*addItemDecoration(
                ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                    resources.getInteger(R.integer.photo_list_preview_columns)
                )
            )*/
            adapter = serviceAdapter
            serviceAdapter.onItemClick = { id, name ->
                navigateToServiceDetailFragment(id, name)
            }
        }
    }

    override fun initListener() {

    }

    override fun setUpObserver() {
        observe(viewModel.apiCategories, ::onCategoriesResponseSuccess)
    }

    private fun navigateToServiceDetailFragment(id: Int, serviceName: String?) {
        navController().currentDestination?.getAction(R.id.action_homeFragment_to_serviceDetailFragment)
            ?.let {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToServiceDetailFragment(serviceName)
                navController().navigate(action)
            }
    }

    private fun onCategoriesResponseSuccess(event: SingleEvent<CategoriesResponse>) {
        event.contentIfNotHandled?.let {
            if (it.success) {
                if (it.data?.categoriesList == null || it.data?.categoriesList?.size == 0)
                    viewModel.isRVVisible.value = false
                else {
                    viewModel.isRVVisible.value = true
                    setUpRecyclerView(it.data?.categoriesList)
                }
            } else
                showToastMessage(it.message)
        }
    }
}