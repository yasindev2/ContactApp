package uz.yasindev.contactapp.core.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.core.models.RvImageModel

interface UserService {

    @GET("/contact/contacts")
    fun getAllUser(): Call<List<RvContactModel>>

    @POST("/contact/contacts")
    fun addUser(@Body rvModel: RvContactModel): Call<RvContactModel>

    @DELETE("/contact/contacts/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>

    @DELETE("/contact/contacts")
    fun deleteAllUsers(): Call<Void>

    @GET("/contact/profile_photos")
    fun getAllImages():List<RvImageModel>

    @PUT("/contact/contacts/{id}")
    fun updateUserData(@Path("id") id:Int,@Body rvModel: RvContactModel):Call<RvContactModel>

    @POST("/")
    fun addPhotosToServer(rvImageModel: RvImageModel)


}