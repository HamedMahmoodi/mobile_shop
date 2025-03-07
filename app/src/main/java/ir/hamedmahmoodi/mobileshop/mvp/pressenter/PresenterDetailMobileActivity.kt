package ir.hamedmahmoodi.mobileshop.mvp.pressenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.SendRequests
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.MobileMainModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestFavorite
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelDetailMobileActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewDetailMobileActivity

class PresenterDetailMobileActivity(
    private val view: ViewDetailMobileActivity,
    private val model: ModelDetailMobileActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils, SendRequests {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getDataMobile()

    }

    override fun activeNetwork() {
        getDataMobile()
    }

    private fun getDataMobile() {

        val result =
            object : CallbackRequest<MobileMainModel> {

                override fun onSuccess(code: Int, data: MobileMainModel) {
                    view.endGetData()
                    view.setData(data.mobile, this@PresenterDetailMobileActivity)
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

        model.getDetailMobile(

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

        model.setMobileFavorite(
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

        model.setMobileComment(
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