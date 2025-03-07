package ir.hamedmahmoodi.mobileshop.mvp.model

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.MobileApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.MobileMainModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestFavorite
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest

class ModelDetailMobileActivity(private val id: Int) {

    companion object {

        const val ACTION_FAVORITE = "favorite"
        const val ACTION_UN_FAVORITE = "unfavorite"

    }

    fun getDetailMobile(
        callbackRequest: CallbackRequest<MobileMainModel>,
        uId: String,
        pubKey: String,
        apiKey: String
    ) {
        MobileApiRepository.instance.getMobileDetail(
            callbackRequest, id, apiKey, uId, pubKey
        )
    }

    fun setMobileFavorite(
        callbackRequest: CallbackRequest<RequestFavorite>,
        apiKey: String,
        uId: String,
        pubKey: String,
        action: String
    ) {
        MobileApiRepository.instance.setMobileFavorite(
            callbackRequest, apiKey, uId, pubKey, action, id
        )
    }

    fun setMobileComment(
        apiKey: String,
        uId: String,
        pubKey: String,
        postId: Int,
        content: String,
        rate: Float,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {
        MobileApiRepository.instance.setMobileComments(
            apiKey, uId, pubKey, postId, content, rate, callbackRequest
        )
    }

}