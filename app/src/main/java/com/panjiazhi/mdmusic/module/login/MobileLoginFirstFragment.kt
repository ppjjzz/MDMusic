package com.panjiazhi.mdmusic.module.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.panjiazhi.mdmusic.R
import com.panjiazhi.mdmusic.databinding.MobileLoginFirstFragmentBinding
import kotlinx.android.synthetic.main.mobile_login_first_fragment.*

class MobileLoginFirstFragment : Fragment() {
    private lateinit var mobileLoginViewModel: MobileLoginViewModel
    companion object {
        fun newInstance() = MobileLoginFirstFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mobileLoginViewModel = (activity as LoginActivity).mobileLoginViewModel
        val binding = MobileLoginFirstFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mobileLoginViewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mobileLoginNextButton.setOnClickListener { v ->
            if (mobileLoginViewModel.phoneNumber.value?.isEmpty()!!) {
                Toast.makeText(context, "请输入手机号", Toast.LENGTH_LONG).show()
            } else {
                v.findNavController().navigate(R.id.mobileLoginSecondFragment)
            }
            Log.d("phoneNumber", mobileLoginViewModel.phoneNumber.value)

        }
    }


}