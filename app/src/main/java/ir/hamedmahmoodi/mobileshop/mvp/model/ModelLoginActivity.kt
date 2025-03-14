package ir.hamedmahmoodi.mobileshop.mvp.model

import android.content.Context
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo

class ModelLoginActivity(
    private val context: Context,
) {

    fun getDeviceInfo() = DeviceInfo()

    fun getUID() = DeviceInfo.getDeviceID(context)

    fun getPublicKey() = DeviceInfo.getPublicKeyWithoutApi(context)

}