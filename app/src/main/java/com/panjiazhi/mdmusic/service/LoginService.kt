package com.panjiazhi.mdmusic.service

import com.panjiazhi.mdmusic.model.bean.LoginRequestModel
import com.panjiazhi.mdmusic.model.bean.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login/cellphone")
    fun login(@Body loginRequestModel: LoginRequestModel): Call<LoginResponse>
}