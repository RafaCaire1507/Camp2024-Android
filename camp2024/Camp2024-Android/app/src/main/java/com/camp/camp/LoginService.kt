package com.camp.camp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST("realms/camp-ioasys-2024/protocol/openid-connect/token")
    fun login(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("scope") scope: String,
    ): Call<LoginResponse>
}