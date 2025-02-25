package ir.hamedmahmoodi.mobileshop.mvp.model

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.MainApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestMain
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest

class ModelHomeFragment {

    fun getContent(callbackRequest: CallbackRequest<RequestMain>) {
        MainApiRepository.instance.getMainContent(callbackRequest)
    }

}