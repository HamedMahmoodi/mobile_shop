package ir.hamedmahmoodi.mobileshop.mvp.model

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.ProductApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductMainModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestFavorite
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest

class ModelDetailProductActivity(private val id: Int) {

    companion object {

        const val ACTION_FAVORITE = "favorite"
        const val ACTION_UN_FAVORITE = "unfavorite"

    }

    fun getDetailProduct(
        callbackRequest: CallbackRequest<ProductMainModel>,
        uId: String,
        pubKey: String,
        apiKey: String
    ) {
        ProductApiRepository.instance.getProductDetail(
            callbackRequest, id, apiKey, uId, pubKey
        )
    }

    fun setProductFavorite(
        callbackRequest: CallbackRequest<RequestFavorite>,
        apiKey: String,
        uId: String,
        pubKey: String,
        action: String
    ) {
        ProductApiRepository.instance.setProductFavorite(
            callbackRequest, apiKey, uId, pubKey, action, id
        )
    }

    fun setProductComment(
        apiKey: String,
        uId: String,
        pubKey: String,
        postId: Int,
        content: String,
        rate: Float,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {
        ProductApiRepository.instance.setProductComments(
            apiKey, uId, pubKey, postId, content, rate, callbackRequest
        )
    }

}