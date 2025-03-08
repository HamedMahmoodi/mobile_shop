package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName

data class AllProductsModel(
    val message: String,
    val page: Int,
    val pages: Int,
    val total: Int,
   @SerializedName("pastries") val products: ArrayList<ProductModel>
)

data class ProductModel(
    @SerializedName("ID") val id: Int,
    val title: String,
    @SerializedName("date_l10n") val dateL10n: String,
    val price: Int,
    @SerializedName("sale_price") val salePrice: Int,
    @SerializedName("active_stock") val activeStock: Boolean,
    @SerializedName("has_discount") val hasDiscount: Boolean,
    val discount: String,
    val stock: Int,
    @SerializedName("min_order") val minOrder: Int,
    val status: String,
    val thumbnail: String
)