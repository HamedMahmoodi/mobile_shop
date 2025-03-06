package ir.hamedmahmoodi.mobileshop.mvp.model

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.AddressApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.Address
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest

class ModelAddressActivity {
    fun getAddress(
        apiKey: String,
        id: String,
        pubKey: String,
        callbackRequest: CallbackRequest<Address>
    ) {

        AddressApiRepository.instance.getAddresses(
            apiKey,
            id,
            pubKey,
            callbackRequest
        )

    }
}