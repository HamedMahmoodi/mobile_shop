package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName


data class RequestSendPhone(
    val success: Int,
    val message: String,
    val seconds: Int,
    @SerializedName("expire_at") val expireAT: String,
)

data class RequestVerifyCode(
    val message: String,
    var api: String
)