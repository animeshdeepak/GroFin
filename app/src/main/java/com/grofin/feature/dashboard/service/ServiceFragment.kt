package com.grofin.feature.dashboard.service

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentServiceBinding
import com.grofin.feature.dashboard.service.adapter.ItemDecorationAlbumColumns
import com.grofin.feature.dashboard.service.adapter.ServiceAdapter
import com.grofin.feature.dashboard.service.model.ServiceModel
import com.grofin.feature.dashboard.ui.HomeFragmentDirections
import com.grofin.feature.response.CategoriesResponse

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

    private fun setUpRecyclerView() {
        binding.rvServices.apply {
            serviceAdapter = ServiceAdapter(getServiceModelList())
            setHasFixedSize(true)
            addItemDecoration(
                ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                    resources.getInteger(R.integer.photo_list_preview_columns)
                )
            )
            adapter = serviceAdapter
            serviceAdapter.onItemClick = { serviceName ->
                navigateToServiceDetailFragment(serviceName)
            }
        }
    }

    private fun getServiceModelList(): ArrayList<ServiceModel> {
        val list = ArrayList<ServiceModel>()
        list.add(ServiceModel("", "Insurance", Constants.MOTORS_URL, R.drawable.insurance))
        list.add(ServiceModel("", "Instant Loan", Constants.MOTORS_URL, R.drawable.insurance))
        list.add(ServiceModel("", "Credit Card", "", R.drawable.insurance))
        list.add(ServiceModel("", "Download and Earn", "", R.drawable.insurance))
        list.add(ServiceModel("", "Investment", "", R.drawable.insurance))
        list.add(ServiceModel("", "Tax Planning", "", R.drawable.insurance))
        return list
    }

    override fun initListener() {

    }

    override fun setUpObserver() {
        observe(viewModel.apiCategories, ::onCategoriesResponseSuccess)
    }

    private fun navigateToServiceDetailFragment(serviceName: String?) {
        navController().currentDestination?.getAction(R.id.action_homeFragment_to_serviceDetailFragment)?.let {
            val action = HomeFragmentDirections.actionHomeFragmentToServiceDetailFragment(serviceName)
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
                    setUpRecyclerView()
                }
            } else
                showToastMessage(it.message)
        }
    }
}