package com.panjiazhi.mdmusic.module.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.panjiazhi.mdmusic.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requireActivity().let {
            it.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mobileLoginNextButton.setOnClickListener { v ->
            v.findNavController().navigate(R.id.mobileLoginFirstFragment)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}