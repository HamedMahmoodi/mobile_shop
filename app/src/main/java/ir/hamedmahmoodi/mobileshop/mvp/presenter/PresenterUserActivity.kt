package ir.hamedmahmoodi.mobileshop.mvp.presenter

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.StartSetUserInfo
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.UserInfoData
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelUserActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewUserActivity

class PresenterUserActivity(
    private val view: ViewUserActivity,
    private val model: ModelUserActivity,
    private val context: Context,
) : BaseLifeCycle, ActivityUtils, StartSetUserInfo {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getUserInfo()

    }

    override fun activeNetwork() {
        getUserInfo()
    }

    private fun getUserInfo() {

        model.getUserInfo(

            object : CallbackRequest<UserInfoData> {

                override fun onSuccess(code: Int, data: UserInfoData) {
                    view.endGetData()
                    view.setUserData(data.user, this@PresenterUserActivity)
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

    override fun startSetUser(
        name: String, email: String, day: String, month: String, year: String, sex: Int
    ) {

        model.setUserInfo(
            name, email, day, month, year, sex,

            object : CallbackRequest<DefaultModel> {

                override fun onSuccess(code: Int, data: DefaultModel) {
                    view.endSetUserInfoSuccess()
                    ToastUtils.toast(context, data.message)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endSetUserInfoNotSuccess()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    ToastUtils.toastServerError(context)
                }

            }

        )

    }

}