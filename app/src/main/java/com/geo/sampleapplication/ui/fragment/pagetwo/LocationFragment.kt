package com.geo.sampleapplication.ui.fragment.pagetwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.geo.sampleapplication.databinding.DialogFragmentLocationBinding
import com.geo.sampleapplication.network.response.Latlng
import com.geo.sampleapplication.ui.base.BaseFragment
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import dagger.hilt.android.AndroidEntryPoint
import ir.map.sdk_map.MapirStyle

@AndroidEntryPoint
class LocationFragment : BaseFragment<DialogFragmentLocationBinding>(), OnMapReadyCallback,
    MapboxMap.OnMapClickListener{

    var map: MapboxMap? = null
    var mapStyle: Style? = null

    companion object {
        fun newInstance(latlng: Latlng): LocationFragment{
            val args = Bundle()
            args.putParcelable("latlon", latlng)
            val fragment = LocationFragment()
            fragment.arguments = args
            return fragment
        }
    }



    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = DialogFragmentLocationBinding.inflate(inflater, container, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding?.mapView?.onCreate(savedInstanceState)
        binding?.mapView?.getMapAsync(this)
    }


    override fun onMapReady(mapboxMap: MapboxMap) {
        map = mapboxMap
        map?.setStyle(Style.Builder().fromUri(MapirStyle.LIGHT)) { style ->
            mapStyle = style

        }
    }

    override fun onMapClick(point: LatLng): Boolean {
        return true
    }



}