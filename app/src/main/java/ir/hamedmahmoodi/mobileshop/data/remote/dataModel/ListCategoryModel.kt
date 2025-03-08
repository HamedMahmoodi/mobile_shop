package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName

data class ListProductsModel(
    val message: String,
    val category: CategoryModel
)

data class CategoryModel(
   @SerializedName("ID") val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int,
   @SerializedName("pastries") val products: ArrayList<ProductModel>
)