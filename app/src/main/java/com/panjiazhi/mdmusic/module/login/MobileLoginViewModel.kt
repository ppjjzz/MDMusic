package com.panjiazhi.mdmusic.module.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MobileLoginViewModel : ViewModel() {
    val phoneNumber = MutableLiveData("")
    val password = MutableLiveData("")
    val canLogin = MutableLiveData(false)
}