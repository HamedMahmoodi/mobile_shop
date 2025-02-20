package ir.hamedmahmoodi.mobileshop.mvp.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.androidWrapper.NetworkInfo
import ir.hamedmahmoodi.mobileshop.data.local.preferences.SecureSharePref
import ir.hamedmahmoodi.mobileshop.data.local.preferences.SharedPrefKey
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.LoginApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.DefaultModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestSendPhone
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestVerifyCode
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.data.remote.ext.ErrorUtils
import ir.hamedmahmoodi.mobileshop.data.remote.mainService.RetrofitService
import ir.hamedmahmoodi.mobileshop.databinding.ActivityLoginBinding
import ir.hamedmahmoodi.mobileshop.databinding.CustomDialogLoginBinding
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils
import ir.hamedmahmoodi.mobileshop.ui.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewLoginActivity(
    contextInstance: Context,
) : FrameLayout(contextInstance), ActivityUtils {

    private var inflater = LayoutInflater.from(context)
    val binding = ActivityLoginBinding.inflate(inflater)

    private lateinit var number: String
    private lateinit var deviceInfo: DeviceInfo

    private var resendState = false

    fun setDeviceInfo(info: DeviceInfo) {
        deviceInfo = info
    }

    //جهت ارسال کد تایید
    fun pressedSendCode(id: String, key: String) {

        binding.btnLogin.getView().setOnClickListener {

            number = binding.inputTextPhone.getText()

            if (numberValidation(number)) {
                if (isCheckedNetwork()) {
                    binding.btnLogin.enableProgress()
                    sendCode(true, id, key)
                }
            }

        }

    }

    //جهت کنترل شماره موبایل وارد شده توسط کاربر
    private fun numberValidation(number: String): Boolean {

        if (number.isEmpty()) {
            binding.inputTextPhone.setError("شماره همراه خود را وارد کنید")
            return false
        }

        if (number.length < 11) {
            binding.inputTextPhone.setError("شماره را صحیح وارد کنید")
            return false
        }

        if (!number.matches(Regex("(\\+98|0)?9\\d{9}"))) {
            binding.inputTextPhone.setError("شماره را صحیح وارد کنید")
            return false
        }

        binding.inputTextPhone.setError(null)

        return true

    }

    //جهت کنترل فعال یا غیر فعال بودن اینترنت کاربر
    private fun isCheckedNetwork() = NetworkInfo.internetInfo(context, this)

    //ارسال کد تایید برای کاربر
    private fun sendCode(dialog: Boolean, id: String, key: String) {

        LoginApiRepository.instance.sendPhoneAuth(
            id,
            key,
            number,
            object : CallbackRequest<RequestSendPhone> {

                override fun onSuccess(code: Int, data: RequestSendPhone) {
                    binding.btnLogin.disableProgress()
                    if (dialog)
                        createDialog(id, key)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    binding.btnLogin.disableProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    binding.btnLogin.disableProgress()
                    ToastUtils.toastServerError(context)
                }

            }
        )

    }

    //نمایش دیالوگ جهت وارد کردن کد ارسالی به کاربر
    @SuppressLint("SetTextI18n")
    private fun createDialog(id: String, key: String) {

        val view = CustomDialogLoginBinding.inflate(inflater)

        view.txtShowNumber.text = "کد تایید به $number ارسال شد"
        view.txtResend.setTextColor(Color.parseColor("#D9888383"))

        // Run Timer
        createTimer(view, id, key)

        val dialog = Dialog(context)
        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.btnConfirm.getView().setOnClickListener {

            val code = view.edtEnterCode.text.toString()

            if (code.length < 5) {
                view.inputEnterCode.error = "کد 5 رقمی را وارد کنید"
                return@setOnClickListener
            } else
                view.inputEnterCode.error = null

            if (isCheckedNetwork()) {

                view.btnConfirm.enableProgress()

                LoginApiRepository.instance.verifyCodeAuth(
                    code,
                    number,
                    object : CallbackRequest<RequestVerifyCode> {

                        override fun onSuccess(code: Int, data: RequestVerifyCode) {

                            view.btnConfirm.disableProgress()

                            dialog.dismiss()

                            val nameView = CustomDialogLoginBinding.inflate(inflater)
                            val nameDialog = Dialog(context)
                            nameDialog.setContentView(nameView.root)
                            nameDialog.setCancelable(false)

                            createNameView(nameView)

                            nameDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            nameDialog.show()

                            val shared = SecureSharePref.getSharedPref(context)
                            val edit = shared.edit().putString(SharedPrefKey.API_KEY, data.api)
                            edit.apply()

                            nameView.btnConfirm.getView().setOnClickListener {

                                val name = nameView.edtEnterCode.text.toString().trim()

                                if (name.isEmpty() || name.length < 3)
                                    nameView.inputEnterCode.error = "لطفا نام خود را وارد کنید"
                                else
                                    nameView.inputEnterCode.error = null

                                if (isCheckedNetwork()) {

                                    nameView.btnConfirm.enableProgress()

                                    LoginApiRepository.instance.editUser(
                                        DeviceInfo.getApi(context),
                                        DeviceInfo.getDeviceID(context),
                                        DeviceInfo.getPublicKey(context),
                                        name,
                                        object : CallbackRequest<DefaultModel> {

                                            override fun onSuccess(code: Int, data: DefaultModel) {

                                                nameView.btnConfirm.disableProgress()

                                                val editLogin = shared.edit()
                                                editLogin.putBoolean(
                                                    SharedPrefKey.LOGIN_STATE_KEY,
                                                    true
                                                )
                                                editLogin.apply()

                                                context.startActivity(
                                                    Intent(context, MainActivity::class.java)
                                                )

                                            }

                                            override fun onNotSuccess(
                                                code: Int,
                                                error: String,
                                            ) {
                                                nameView.btnConfirm.disableProgress()
                                                ToastUtils.toast(context, error)
                                            }

                                            override fun onError(error: String) {
                                                nameView.btnConfirm.disableProgress()
                                                ToastUtils.toastServerError(context)
                                            }

                                        }
                                    )

                                }

                            }

                        }

                        override fun onNotSuccess(code: Int, error: String) {
                            view.btnConfirm.disableProgress()
                            ToastUtils.toast(context, error)
                        }

                        override fun onError(error: String) {
                            view.btnConfirm.disableProgress()
                            ToastUtils.toastServerError(context)
                        }

                    }
                )

            }

        }

        view.txtEditPhone.setOnClickListener {
            dialog.dismiss()
        }

    }

    //قراردادن تایمر شمارش معکوس جهت وارد کردن کد راستی آزمایی ارسالی به کاربر
    @SuppressLint("SetTextI18n")
    private fun createTimer(
        view: CustomDialogLoginBinding,
        id: String,
        key: String,
    ) {

        object : CountDownTimer(180000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val minute = (millisUntilFinished / 1000) / 60
                val second = (millisUntilFinished / 1000) % 60
                if (second >= 10)
                    view.txtTime.text = "0$minute:$second"
                else
                    view.txtTime.text = "0$minute:0$second"

            }

            override fun onFinish() {
                view.txtTime.text = "00:00"
                resendState = true
                view.txtResend.setTextColor(Color.parseColor("#101219"))
                view.txtResend.setOnClickListener {
                    if (resendState) {
                        view.txtResend.setTextColor(Color.parseColor("#D9888383"))
                        resendState = false
                        sendCode(false, id, key)
                        createTimer(view, id, key)
                    }
                }
            }

        }.start()

    }

    private fun createNameView(nameView: CustomDialogLoginBinding) {

        nameView.txtResend.visibility = GONE
        nameView.txtTime.visibility = GONE
        nameView.txtEditPhone.visibility = GONE
        nameView.txtShowNumber.visibility = GONE
        nameView.edtEnterCode.inputType = InputType.TYPE_CLASS_TEXT
        nameView.textView.text = "اطلاعات کاربری"
        nameView.edtEnterCode.hint = "نام و نام خانوادگی"
        nameView.edtEnterCode.gravity = Gravity.START
        nameView.edtEnterCode.textDirection = TEXT_DIRECTION_RTL
        nameView.edtEnterCode.filters = arrayOf(InputFilter.LengthFilter(40))
        nameView.btnConfirm.getView().text = "ثبت اطلاعات"

    }

    fun editUser(
        apiKey: String,
        id: String,
        pubKey: String,
        fullName: String,
        callbackRequest: CallbackRequest<DefaultModel>,
    ) {

        RetrofitService.apiService.editUser(apiKey, id, pubKey, fullName).enqueue(

            object : Callback<DefaultModel> {

                override fun onResponse(
                    call: Call<DefaultModel>,
                    response: Response<DefaultModel>,
                ) {

                    if (response.isSuccessful)
                        callbackRequest.onSuccess(
                            response.code(),
                            response.body() as DefaultModel
                        )
                    else {
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