package ir.hamedmahmoodi.mobileshop.mvp.presenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.AllProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ListProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelListProductActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewListProductActivity

class PresenterListProductActivity(
    private val view: ViewListProductActivity,
    private val model: ModelListProductActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {
        showNavigationDrawer()
        onBackClick()
        getData()
    }

    private fun showNavigationDrawer() {
        view.showNavDrawer()
    }

    private fun getData() {

        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getProducts()

    }

    override fun activeNetwork() {
        getProducts()
    }

    private fun onBackClick() {
        view.onBack()
    }

    private fun getProducts() {

        model.getProducts(

            object : CallbackRequest<ListProductsModel> {

                override fun onSuccess(code: Int, data: ListProductsModel) {
                    view.endGetData()
                    view.setData(data)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProgress()
                    ToastUtils.toastServerError(context)
                }

            },

            object : CallbackRequest<AllProductsModel> {

                override fun onSuccess(code: Int, data: AllProductsModel) {
                    view.endGetData()
                    view.setData2(data, model.getTitle())
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