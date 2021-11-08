package com.grofin.feature.dashboard.service

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.databinding.FragmentServiceBinding
import com.grofin.feature.dashboard.service.adapter.ItemDecorationAlbumColumns
import com.grofin.feature.dashboard.service.adapter.ServiceAdapter
import com.grofin.feature.dashboard.service.model.ServiceModel

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
    }

    override fun initViews() {
        setUpRecyclerView()
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
            serviceAdapter.onItemClick = { name ->
/*                Intent(requireContext(), WebViewActivity::class.java).apply {
                    putExtra(WebViewActivity.WEB_VIEW_TITLE, name)
                }.run {
                    startActivity(this)
                }*/
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

    }
}