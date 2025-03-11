package ir.hamedmahmoodi.mobileshop.mvp.presenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelHomeFragment
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewHomeFragment

class PresenterHomeFragment(
    private val view: ViewHomeFragment,
    private val model: ModelHomeFragment,
    private val context: Context,
):BaseLifeCycle,ActivityUtils {
    override fun onCreate() {
        createSlider()
    }

    private fun createSlider() {

    /*    view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            sendRequest()*/

    }

    override fun activeNetwork() {
        sendRequest()
    }

    private fun sendRequest() {

   /*     model.getContent(
            object : CallbackRequest<RequestMain> {

                override fun onSuccess(code: Int, data: RequestMain) {
                    view.endGetData()
                    view.initialized(data)
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
        )*/

    }
}