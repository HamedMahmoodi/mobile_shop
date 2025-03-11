package ir.hamedmahmoodi.mobileshop.mvp.model

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.ProductListApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.AllProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest

class ModelFavoriteActivity {

    fun getProducts(
        apiKey: String,
        pubKey: String,
        id: String,
        callbackRequest: CallbackRequest<AllProductsModel>
    ) {

        ProductListApiRepository.instance.getFavoriteContent(
            apiKey,
            pubKey,
            id,
            callbackRequest
        )

    }

}