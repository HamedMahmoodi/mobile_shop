package ir.hamedmahmoodi.mobileshop.mvp.presenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.AllProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelFavoriteActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewFavoriteActivity

class PresenterFavoriteActivity(
    private val view: ViewFavoriteActivity,
    private val model: ModelFavoriteActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getProducts()

    }

    override fun onStart() {

        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getProducts()

    }

    override fun activeNetwork() {
        getProducts()
    }

    private fun getProducts() {

        model.getProducts(

            DeviceInfo.getApi(context),
            DeviceInfo.getPublicKey(context),
            DeviceInfo.getDeviceID(context),
            object : CallbackRequest<AllProductsModel> {

                override fun onSuccess(code: Int, data: AllProductsModel) {
                    view.endGetData()
                    view.setDataRecycler(data.products)
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