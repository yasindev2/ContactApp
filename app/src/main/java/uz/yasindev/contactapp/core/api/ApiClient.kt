package uz.yasindev.contactapp.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6683f18356e7503d1adec549.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    fun getService(): UserService {
        return getRetrofit().create(UserService::class.java)
    }

}