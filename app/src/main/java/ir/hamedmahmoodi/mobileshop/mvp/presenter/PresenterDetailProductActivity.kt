package ir.hamedmahmoodi.mobileshop.mvp.presenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.SendRequests
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductMainModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestFavorite
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelDetailProductActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewDetailProductActivity

class PresenterDetailProductActivity(
    private val view: ViewDetailProductActivity,
    private val model: ModelDetailProductActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils, SendRequests {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getDataProduct()

    }

    override fun activeNetwork() {
        getDataProduct()
    }

    private fun getDataProduct() {

        val result =
            object : CallbackRequest<ProductMainModel> {

                override fun onSuccess(code: Int, data: ProductMainModel) {
                    view.endGetData()
                    view.setData(data.product, this@PresenterDetailProductActivity)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProgress()
                    ToastUtils.toastServerError(context)
                }

            }

        model.getDetailProduct(

            result,
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            DeviceInfo.getApi(context)

        )

    }

    override fun startSendFavorite(
        uId: String,
        pubKey: String,
        apiKey: String,
        action: String
    ) {

        val result = object : CallbackRequest<RequestFavorite> {

            override fun onSuccess(code: Int, data: RequestFavorite) {
                ToastUtils.toast(context, data.message)
            }

            override fun onNotSuccess(code: Int, error: String) {
                ToastUtils.toast(context, error)
            }

            override fun onError(error: String) {
                ToastUtils.toastServerError(context)
            }

        }

        model.setProductFavorite(
            result,
            apiKey,
            uId,
            pubKey,
            action
        )

    }

    override fun sendComment(
        uId: String,
        pubKey: String,
        apiKey: String,
        content: String,
        rate: Float,
        postId: Int
    ) {

        model.setProductComment(
            apiKey, uId, pubKey, postId, content, rate,

            object : CallbackRequest<DefaultModel> {

                override fun onSuccess(code: Int, data: DefaultModel) {
                    view.disableButtonProgress()
                    ToastUtils.toast(context, data.message)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.disableButtonProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.disableButtonProgress()
                    ToastUtils.toastServerError(context)
                }

            }

        )

    }

}