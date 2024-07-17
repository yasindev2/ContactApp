package uz.yasindev.contactapp.core.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RvContactModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar")
    val image: String,
    @SerializedName("name")
    val contactName: String,
    @SerializedName("phone_number")
    val phoneNumber: String
):Serializable
