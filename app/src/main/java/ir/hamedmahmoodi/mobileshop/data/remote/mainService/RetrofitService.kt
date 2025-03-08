package ir.hamedmahmoodi.mobileshop.data.remote.mainService

import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.AddressApiService
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.LoginApiService
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.MainApiService
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.ProductApiService
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.ProductListApiService
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.UserApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private const val url = "https://pastry.alirezaahmadi.info/api/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS) // TIMEOUT برای برقراری اتصال به سرور
        .readTimeout(20, TimeUnit.SECONDS) // TIMEOUT برای دریافت داده از سرور
        .writeTimeout(30, TimeUnit.SECONDS) // TIMEOUT برای ارسال داده به سرور
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: LoginApiService = retrofit.create(LoginApiService::class.java)
    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)
    val mainApiService: MainApiService = retrofit.create(MainApiService::class.java)
    val addressApiService: AddressApiService = retrofit.create(AddressApiService::class.java)
    val productApiService: ProductApiService = retrofit.create(ProductApiService::class.java)
    val productsListApiService: ProductListApiService = retrofit.create(ProductListApiService::class.java)
  /*
    val catsApiService: CatsApiService = retrofit.create(CatsApiService::class.java)
    val cakeApiService: CakeApiService = retrofit.create(CakeApiService::class.java)
    */

}