package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName

data class MainProductsModel(
    @SerializedName("ID") val id: String,
    val title: String,
    val pastries: ArrayList<ProductsModel>,
)

data class BannersModel(
    @SerializedName("ID") val id: String,
    val large: String,
)

data class ProductsModel(
    @SerializedName("ID") val id: Int,
    val title: String,
    @SerializedName("min_order") val minOrder: Int,
    val thumbnail: String,
    val price: Int,
    @SerializedName("sale_price") val salePrice: Int,
    @SerializedName("has_discount") val hasDiscount: Boolean,
    val discount: String,
)

data class RequestMain(
    val success: Int,
    val message: String,
    val sliders: ArrayList<String>,
    val pastries: ArrayList<MainProductsModel>,
    val banners: ArrayList<BannersModel>,
)