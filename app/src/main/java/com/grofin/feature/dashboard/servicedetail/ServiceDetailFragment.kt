package com.grofin.feature.dashboard.servicedetail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.databinding.FragmentServiceDetailBinding
import com.grofin.feature.dashboard.service.ServiceViewModel

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