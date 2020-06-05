package com.panjiazhi.mdmusic.module.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.panjiazhi.mdmusic.common.config.AppConfig
import com.panjiazhi.mdmusic.common.net.RetrofitFactory
import com.panjiazhi.mdmusic.common.utils.MyPreference
import com.panjiazhi.mdmusic.databinding.FragmentMobileLoginSecondBinding
import com.panjiazhi.mdmusic.model.bean.LoginRequestModel
import com.panjiazhi.mdmusic.service.LoginService
import kotlinx.android.synthetic.main.fragment_mobile_login_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class MobileLoginSecondFragment : Fragment() {
    private var cookie: String by MyPreference(AppConfig.APP_COOKIE, "")
    private var isLogin by MyPreference(AppConfig.IS_LOGIN, false)
    private lateinit var mobileLoginViewModel: MobileLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mobileLoginViewModel = (activity as LoginActivity).mobileLoginViewModel
        val binding = FragmentMobileLoginSecondBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mobileLoginViewModel
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        mobileLoginViewModel.password.observe(viewLifecycleOwner, Observer { password ->
            mobileLoginViewModel.canLogin.value = password.isNotEmpty()
        })
        mobileLoginButton.setOnClickListener { v ->
            if (mobileLoginViewModel.canLogin.value!!) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val loginRequestModel = LoginRequestModel(
                            mobileLoginViewModel.phoneNumber.value!!,
                            mobileLoginViewModel.password.value!!
                        )
                        val result = RetrofitFactory.createService<LoginService>().login(loginRequestModel).await()
                        cookie = "MUSIC_U=" + result.token
                        isLogin = true
                        Log.d("token", result.token)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MobileLoginSecondFragment()
    }
}