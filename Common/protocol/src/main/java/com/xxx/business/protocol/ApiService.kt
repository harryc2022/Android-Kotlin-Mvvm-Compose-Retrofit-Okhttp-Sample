package com.xxx.business.protocol

import com.xxx.business.protocol.data.UserInfo
import com.xxx.business.protocol.net.ApiResponse
import com.xxx.common.protocol.BuildConfig.DOMAIN
import com.xxx.framework.network.NetworkApi
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object{
        val api: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi.INSTANCE.getApi(ApiService::class.java, DOMAIN)
        }
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): ApiResponse<UserInfo>
}