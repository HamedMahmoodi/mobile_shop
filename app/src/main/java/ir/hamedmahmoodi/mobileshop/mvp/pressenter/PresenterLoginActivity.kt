package ir.hamedmahmoodi.mobileshop.mvp.pressenter

import ir.hamedmahmoodi.mobileshop.mvp.ext.BaseLifeCycle
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelLoginActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewLoginActivity

class PresenterLoginActivity(
    private val view: ViewLoginActivity,
    private val model: ModelLoginActivity,
) : BaseLifeCycle {

    override fun onCreate() {
        sendDeviceInfo()
        onClickSendCode()
    }

    private fun sendDeviceInfo() {
        view.setDeviceInfo(model.getDeviceInfo())
    }

    private fun onClickSendCode() {
        view.pressedSendCode(model.getUID(), model.getPublicKey())
    }

}