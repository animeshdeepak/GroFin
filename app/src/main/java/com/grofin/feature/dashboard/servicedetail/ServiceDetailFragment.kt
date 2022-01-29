package com.grofin.feature.dashboard.servicedetail

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.databinding.FragmentServiceDetailBinding
import com.grofin.feature.dashboard.service.ServiceViewModel
import com.grofin.feature.dashboard.service.adapter.ItemDecorationAlbumColumns
import com.grofin.feature.webview.WebViewActivity

class ServiceDetailFragment : BaseFragment<FragmentServiceDetailBinding, ServiceViewModel>() {
    override fun getLayoutId() = R.layout.fragment_service_detail

    override fun getViewModelClass() = ServiceViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    private val args: ServiceDetailFragmentArgs by navArgs()

    override fun executeOnlyOnce() {
        initViews()
        initListener()
        setUpObserver()
        setUpTopBanner()
    }

    override fun initViews() {
        binding.subServiceTv.text = args.serviceName
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvServices.apply {
            val subServiceAdapter = SubServiceAdapter(arrayListOf())
            setHasFixedSize(true)
            addItemDecoration(
                ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                    resources.getInteger(R.integer.photo_list_preview_columns)
                )
            )
            adapter = subServiceAdapter
            subServiceAdapter.onItemClick = { serviceName, serviceUrl ->
                openDialog(serviceName, serviceUrl)
            }
        }
    }

    private fun openDialog(serviceName: String?, serviceUrl: String?) {
        val dialog = GetVehicleDialog()
        dialog.apply {
            onDialogOk = {
                /**
                 * TODO(send dialog data to server in future)
                 */
                openWebView(serviceName, serviceUrl)
            }
        }
        dialog.show(childFragmentManager, GetVehicleDialog.TAG)
    }

    private fun openWebView(serviceName: String?, serviceUrl: String?) {
        Intent(requireContext(), WebViewActivity::class.java).apply {
            putExtra(WebViewActivity.WEB_VIEW_TITLE, serviceName)
            putExtra(WebViewActivity.WEB_VIEW_URL, serviceUrl)
        }.run {
            startActivity(this)
        }
    }

    override fun initListener() {

    }

    override fun setUpObserver() {

    }

    private fun setUpTopBanner() {
        val sliderItemList = ArrayList<SlideModel>()
        sliderItemList.add(SlideModel(imagePath = R.drawable.banner_img_1))
        sliderItemList.add(SlideModel(imagePath = R.drawable.banner_img_2))
        sliderItemList.add(SlideModel(imagePath = R.drawable.banner_img_1))

        binding.imageSlider.setImageList(sliderItemList)
    }
}