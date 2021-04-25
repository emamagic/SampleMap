package com.geo.sampleapplication.ui.fragment.pageone

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.geo.sampleapplication.R
import com.geo.sampleapplication.databinding.FragmentPageOneBinding
import com.geo.sampleapplication.network.response.Latlng
import com.geo.sampleapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageOneFragment: BaseFragment<FragmentPageOneBinding>() {

    private val viewModel: PageOnViewModel by viewModels()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentPageOneBinding.inflate(inflater ,container ,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeOnEvent()

        binding?.btnOneSubmit?.setOnClickListener {
            viewModel.onSubmitClicked(binding?.edtOneLat?.text.toString() ,binding?.edtOneLon?.text.toString())
        }

    }

    private fun subscribeOnEvent(){
        viewModel.pageOneState.observe(viewLifecycleOwner) { task ->
            when (task){
                is PageOneState.InvalidInput -> Toast.makeText(requireContext(), task.message , Toast.LENGTH_SHORT).show()

                is PageOneState.NavigateToPageTwoFragment -> {
                    Bundle().also {
                        it.putParcelable("latlon" ,Latlng(task.lat ,task.lon))
                        Navigation.findNavController(requireView()).navigate(R.id.action_pageOneFragment_to_locationFragment ,it)
                    }
                }

                is PageOneState.CheckGPSStatus -> {
                    if (checkGpsStatus()) viewModel.gpsIsEnable()
                    else viewModel.gpsIsDisable()
                }

                is PageOneState.GpsIsDisable -> buildAlertMessageNoGps()
            }
        }
    }


    private fun checkGpsStatus():Boolean {
        val manager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun buildAlertMessageNoGps() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage("برای اتصال به دستگاهباید GPS خود را فعال کنید")
                .setCancelable(false)
                .setPositiveButton(
                        "باشه"
                ) { _, _ ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                }
                .setNegativeButton(
                        "بیخیال"
                ) { dialog, _ -> dialog.cancel() }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

}