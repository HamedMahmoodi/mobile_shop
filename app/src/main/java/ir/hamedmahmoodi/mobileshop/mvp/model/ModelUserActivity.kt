package ir.hamedmahmoodi.mobileshop.mvp.model

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.UserApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.UserInfoData
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest

class ModelUserActivity(private val context: Context) {

    fun getUserInfo(callbackRequest: CallbackRequest<UserInfoData>) {
        UserApiRepository.instance.getUserInfo(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            callbackRequest
        )
    }

    fun setUserInfo(
        name: String,
        email: String,
        day: String,
        month: String,
        year: String,
        sex: Int,
        callbackRequest: CallbackRequest<DefaultModel>,
    ) {
        UserApiRepository.instance.setUserInfo(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            name,
            email,
            day,
            month,
            year,
            sex,
            callbackRequest
        )
    }

}