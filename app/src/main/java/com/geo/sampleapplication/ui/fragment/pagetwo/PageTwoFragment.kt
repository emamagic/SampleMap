package com.geo.sampleapplication.ui.fragment.pagetwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.geo.sampleapplication.databinding.FragmentPageTwoBinding
import com.geo.sampleapplication.network.response.Latlng
import com.geo.sampleapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageTwoFragment: BaseFragment<FragmentPageTwoBinding>() {

    private val viewModel: PageTwoViewModel by viewModels()
    private var latlng: Latlng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        latlng = arguments?.getParcelable("latlon")
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentPageTwoBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        LocationFragment.newInstance(latlng!!)

    }



}