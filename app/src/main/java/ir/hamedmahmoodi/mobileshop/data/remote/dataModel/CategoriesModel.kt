package ir.hamedmahmoodi.mobileshop.data.remote.dataModel

import com.google.gson.annotations.SerializedName

data class ParentCategoryModel(
    val message: String,
    val total: Int,
    val banner: String,
    val categories: ArrayList<CategoriesModel>
)

data class CategoriesModel(
   @SerializedName("ID") val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val count: Int
)