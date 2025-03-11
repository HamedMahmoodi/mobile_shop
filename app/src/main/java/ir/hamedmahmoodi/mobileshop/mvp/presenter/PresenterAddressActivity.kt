package ir.hamedmahmoodi.mobileshop.mvp.presenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.Address
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelAddressActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewAddressActivity

class PresenterAddressActivity(
    private val view: ViewAddressActivity,
    private val model: ModelAddressActivity,
    private val context: Context,
) : BaseLifeCycle, ActivityUtils {
    override fun onCreate() {
        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getAddress()
    }

    override fun onStart() {
        getAddress()
    }

    override fun activeNetwork() {
        getAddress()
    }

    private fun getAddress() {

        model.getAddress(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),

            object : CallbackRequest<Address> {

                override fun onSuccess(code: Int, data: Address) {
                    view.endGetData()
                    view.setDataRecycler(data)
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

        )

    }
}