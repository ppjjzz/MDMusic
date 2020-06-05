package com.panjiazhi.mdmusic.module.discovery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import com.panjiazhi.mdmusic.R

class DiscoveryFragment : Fragment() {

    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    private val viewModel by viewModels<DiscoveryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.discovery_fragment, container, false)
    }



}
