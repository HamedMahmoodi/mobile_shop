package ir.hamedmahmoodi.mobileshop.mvp.pressenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.UserInfoData
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelProfileFragment
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewProfileFragment

class PresenterProfileFragment(
    private val view:ViewProfileFragment,
    val model:ModelProfileFragment,
    private val context: Context
):BaseLifeCycle,ActivityUtils {
    override fun onCreate() {
        onClickHandler()
        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getUserInfo()
    }

    override fun activeNetwork() {
        getUserInfo()
    }

    private fun onClickHandler() {
        view.onClick()
    }

    private fun getUserInfo() {

        model.getUserInfo(

            object : CallbackRequest<UserInfoData> {

                override fun onSuccess(code: Int, data: UserInfoData) {
                    view.endGetData()
                    view.setUserData(data.user)
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