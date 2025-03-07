package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName

data class MobileMainModel(
    val message: String,
    @SerializedName("pastry") val mobile: MobileDetailModel
)

data class MobileDetailModel(
    @SerializedName("ID") val id: Int,
    val title: String,
    val content: String,
    val status: String,
    val excerpt: String,
    @SerializedName("date_i18n") val dateI18n: String,
    val date: String,
    @SerializedName("active_stock") val activeStock: Boolean,
    val stock: Int,
    val price: Int,
    val weight: Int,
    @SerializedName("sale_price") val salePrice: Int,
    @SerializedName("bulk_price") val bulkPrice: ArrayList<BulkPrice>,
    @SerializedName("active_special_discount") val activeSpecialDiscount: Boolean,
    @SerializedName("special_discount_date") val specialDiscountDate: String,
    @SerializedName("special_discount") val specialDiscount: Int,
    @SerializedName("has_discount") val hasDiscount: Boolean,
    @SerializedName("discount_percent") val discountPercent: Int,
    @SerializedName("discount_percent_110n") val discountPercent110n: String,
    @SerializedName("min_order") val minOrder: Int,
    @SerializedName("max_order") val maxOrder: Int,
    @SerializedName("order_step") val orderStep: Int,
    val gallery: ArrayList<String>,
    @SerializedName("materials") val specifications: ArrayList<Specifications>,
    @SerializedName("comment_count") val commentCount: Int,
    val rate: Rate,
    val comments: ArrayList<Comments>?,
    val bookmark: Boolean,
    val categories: ArrayList<Categories>,
    val thumbnail: String,
    val related: ArrayList<Related>
)

data class BulkPrice(
    val amount: Int,
    val price: Int,
    @SerializedName("sale_price") val salePrice: Int
)

data class Specifications(
    @SerializedName("material") val specification: String,
    val amount: String
)

data class Rate(
    val rate: Int,
    val count: String
)

data class Comments(
    @SerializedName("ID") val id: Int,
    val body: String,
    val name: String,
    val date: String,
    val avatar: String,
    val rate: Int,
    @SerializedName("date_i18n") val dateI18n: String
)

data class Categories(
    @SerializedName("ID") val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int
)

data class Related(
    val title: String,
    @SerializedName("min_order") val minOrder: Int,
    val thumbnail: String,
    val price: Int,
    @SerializedName("sale_price") val salePrice: Int
)

data class RequestFavorite(
    val success: Int,
    val message: String
)