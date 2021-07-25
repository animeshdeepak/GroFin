package com.grofin.feature.dashboard.service

import android.content.Intent
import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.databinding.FragmentServiceBinding
import com.grofin.feature.dashboard.service.adapter.ItemDecorationAlbumColumns
import com.grofin.feature.dashboard.service.adapter.ServiceAdapter
import com.grofin.feature.dashboard.service.model.ServiceModel
import com.grofin.feature.webview.WebViewActivity

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
/*            addItemDecoration(
                SpacesItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.photos_list_spacing)
                )
            )*/
            adapter = serviceAdapter
            serviceAdapter.onItemClick = { name, url ->
                Intent(requireContext(), WebViewActivity::class.java).apply {
                    putExtra(WebViewActivity.WEB_VIEW_TITLE, name)
                    putExtra(WebViewActivity.WEB_VIEW_URL, url)
                }.run {
                    startActivity(this)
                }
            }
        }
    }

    private fun getServiceModelList(): ArrayList<ServiceModel> {
        val list = ArrayList<ServiceModel>()
        list.add(ServiceModel("", "Car", Constants.MOTORS_URL, R.drawable.car))
        list.add(ServiceModel("", "2 Wheeler", Constants.MOTORS_URL, R.drawable.motorcycle))
        list.add(ServiceModel("", "Health", "", R.drawable.health))
        list.add(ServiceModel("", "Life/Term", "", R.drawable.insurance))
        list.add(ServiceModel("", "Cards", "", R.drawable.cards))
        list.add(ServiceModel("", "Investment", "", R.drawable.investment))
        return list
    }

    override fun initListener() {

    }

    override fun setUpObserver() {

    }
}