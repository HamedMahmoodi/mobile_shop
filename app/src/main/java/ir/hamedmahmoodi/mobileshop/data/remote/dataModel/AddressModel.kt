package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName

data class Address(
    val message: String,
    val addresses: ArrayList<Addresses>
)

data class Addresses(
    @SerializedName("ID") val id: Int,
    val address: String,
    val receiver: String,
    val phone: String,
    @SerializedName("updated_at") val updatedAT: String,
)
