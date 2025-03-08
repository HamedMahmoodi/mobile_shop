package ir.hamedmahmoodi.mobileshop.data.remote.apiRepository

import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.AllProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ListProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.data.remote.ext.ErrorUtils
import ir.hamedmahmoodi.mobileshop.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

class ProductListApiRepository private constructor() {

    companion object {

        private var apiRepository: ProductListApiRepository? = null

        val instance: ProductListApiRepository
            get() {
                if (apiRepository == null) apiRepository = ProductListApiRepository()
                return apiRepository!!
            }

    }

    fun getMainContent(
        callbackRequest: CallbackRequest<ListProductsModel>,
        id: Int
    ) {

        RetrofitService.productsListApiService.getProducts(id, true).enqueue(

            object : Callback<ListProductsModel> {

                override fun onResponse(
                    call: Call<ListProductsModel>,
                    response: Response<ListProductsModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as ListProductsModel
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    } else {
                        val error = ErrorUtils.parseError(response)
                        callbackRequest.onNotSuccess(
                            response.code(),
                            error
                        )
                    }

                }

                override fun onFailure(call: Call<ListProductsModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun getContentByType(
        callbackRequest: CallbackRequest<AllProductsModel>,
        type: String
    ) {

        RetrofitService.productsListApiService.getProductsByType(type).enqueue(

            object : Callback<AllProductsModel> {

                override fun onResponse(
                    call: Call<AllProductsModel>,
                    response: Response<AllProductsModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as AllProductsModel
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    } else
                        callbackRequest.onNotSuccess(
                            response.code(),
                            response.errorBody().toString()
                        )

                }

                override fun onFailure(call: Call<AllProductsModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun getFavoriteContent(
        apiKey: String,
        pubKey: String,
        id: String,
        callbackRequest: CallbackRequest<AllProductsModel>
    ) {

        RetrofitService.productsListApiService.getFavoriteProducts(
            apiKey,
            id,
            pubKey,
            true
        ).enqueue(

            object : Callback<AllProductsModel> {

                override fun onResponse(
                    call: Call<AllProductsModel>,
                    response: Response<AllProductsModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as AllProductsModel
                        callbackRequest.onSuccess(
                            response.code(),
                            data
                        )
                    } else
                        callbackRequest.onNotSuccess(
                            response.code(),
                            response.errorBody().toString()
                        )

                }

                override fun onFailure(call: Call<AllProductsModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

}

interface ProductListApiService {

    @GET("cat/{id}")
    fun getProducts(
        @Path(value = "id", encoded = false) ID: Int,
        @Query("has_pastries") hasPastries: Boolean
    ): Call<ListProductsModel>

    @GET("pastries")
    fun getProductsByType(
        @Query("orderBy") type: String
    ): Call<AllProductsModel>

    @GET("pastries")
    fun getFavoriteProducts(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Query("favorite") favorite: Boolean
    ): Call<AllProductsModel>

}