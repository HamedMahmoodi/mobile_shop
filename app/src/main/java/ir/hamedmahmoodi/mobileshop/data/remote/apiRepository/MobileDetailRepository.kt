package ir.hamedmahmoodi.mobileshop.data.remote.apiRepository

import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.MobileMainModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestFavorite
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.data.remote.ext.ErrorUtils
import ir.hamedmahmoodi.mobileshop.data.remote.mainService.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

class MobileApiRepository private constructor() {

    companion object {

        private var apiRepository: MobileApiRepository? = null

        val instance: MobileApiRepository
            get() {
                if (apiRepository == null) apiRepository = MobileApiRepository()
                return apiRepository!!
            }

    }

    fun getMobileDetail(
        callbackRequest: CallbackRequest<MobileMainModel>,
        id: Int,
        apiKey: String,
        uId: String,
        pubKey: String,
    ) {

        RetrofitService.mobileApiService.getMobile(
            id,
            uId,
            pubKey,
            apiKey
        ).enqueue(

            object : Callback<MobileMainModel> {

                override fun onResponse(
                    call: Call<MobileMainModel>,
                    response: Response<MobileMainModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as MobileMainModel
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

                override fun onFailure(call: Call<MobileMainModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun setMobileFavorite(
        callbackRequest: CallbackRequest<RequestFavorite>,
        apiKey: String,
        uId: String,
        pubKey: String,
        action: String,
        id: Int
    ) {

        RetrofitService.mobileApiService.setMobileFavorite(id, apiKey, uId, pubKey, action).enqueue(

            object : Callback<RequestFavorite> {

                override fun onResponse(
                    call: Call<RequestFavorite>,
                    response: Response<RequestFavorite>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as RequestFavorite
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

                override fun onFailure(call: Call<RequestFavorite>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

    fun setMobileComments(
        apiKey: String,
        uId: String,
        pubKey: String,
        postId: Int,
        content: String,
        rate: Float,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {

        RetrofitService.mobileApiService.setMobileComment(
            apiKey, uId, pubKey, postId, content, rate
        ).enqueue(

            object : Callback<DefaultModel> {

                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>
                ) {

                    if (response.isSuccessful) {
                        val data = response.body() as DefaultModel
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

                override fun onFailure(call: Call<DefaultModel>, t: Throwable) {

                    callbackRequest.onError(t.message.toString())

                }

            }

        )

    }

}

interface MobileApiService {

    @GET("pastry/{id}")
    fun getMobile(
        @Path(value = "id", encoded = false) ID: Int,
        @Header("app-device-uid") uId: String,
        @Header("app-public-key") pubKey: String,
        @Header("app-api-key") apiKey: String
    ): Call<MobileMainModel>

    @FormUrlEncoded
    @POST("pastry/{id}/operations/")
    fun setMobileFavorite(
        @Path(value = "id", encoded = false) pastryId: Int,
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("action") action: String
    ): Call<RequestFavorite>

    @FormUrlEncoded
    @POST("comment/")
    fun setMobileComment(
        @Header("app-api-key") apiKey: String,
        @Header("app-device-uid") id: String,
        @Header("app-public-key") pubKey: String,
        @Field("post_id") post_id: Int,
        @Field("content") content: String,
        @Field("rate") rate: Float
    ): Call<DefaultModel>

}

interface SendRequests {

    fun startSendFavorite(
        uId: String,
        pubKey: String,
        apiKey: String,
        action: String
    )

    fun sendComment(
        uId: String,
        pubKey: String,
        apiKey: String,
        content: String,
        rate: Float,
        postId: Int
    )

}