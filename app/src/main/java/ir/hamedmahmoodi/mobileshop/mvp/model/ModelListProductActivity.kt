package ir.hamedmahmoodi.mobileshop.mvp.model

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.ProductListApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.AllProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ListProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.ui.activity.ListProductActivity

class ModelListProductActivity(
    private val id: Int,
    private val type: String,
) {

    fun getProducts(
        callbackRequest: CallbackRequest<ListProductsModel>,
        callbackRequest2: CallbackRequest<AllProductsModel>,
    ) {

        if (id != 0)
            ProductListApiRepository.instance.getMainContent(callbackRequest, id)
        else
            ProductListApiRepository.instance.getContentByType(callbackRequest2, type)

    }

    fun getTitle() = when (type) {
        ListProductActivity.NEW -> "تازه ترین ها"
        ListProductActivity.RATE -> "محبوب ترین ها"
        ListProductActivity.SPECIAL_OFFER -> "پیشنهاد ویژه"
        else -> ""
    }

}