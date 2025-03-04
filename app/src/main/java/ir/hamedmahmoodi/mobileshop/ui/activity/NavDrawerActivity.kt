package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.OnBackPressedCallback
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.androidWrapper.DeviceInfo
import ir.hamedmahmoodi.mobileshop.data.remote.apiRepository.UserApiRepository
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.UserInfoData
import ir.hamedmahmoodi.mobileshop.data.remote.ext.CallbackRequest
import ir.hamedmahmoodi.mobileshop.databinding.ActivityNavDrawerBinding
import ir.hamedmahmoodi.mobileshop.mvp.ext.ToastUtils

class NavDrawerActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNavDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        binding.txtAbout.setOnClickListener(this)
        binding.txtContact.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
        binding.txtOrders.setOnClickListener(this)
        binding.txtSupport.setOnClickListener(this)
        binding.txtUpgrade.setOnClickListener(this)
        binding.imgCloseNav.setOnClickListener(this)

        getUserInfo()

        onBack()

    }

    private fun getUserInfo() {

        UserApiRepository.instance.getUserInfo(
            DeviceInfo.getApi(this),
            DeviceInfo.getDeviceID(this),
            DeviceInfo.getPublicKey(this),
            object : CallbackRequest<UserInfoData> {

                override fun onSuccess(code: Int, data: UserInfoData) {
                    binding.txtName.text = data.user.fullname
                    binding.txtPhone.text = data.user.phone
                }

                override fun onNotSuccess(code: Int, error: String) {
                    ToastUtils.toast(this@NavDrawerActivity, error)
                }

                override fun onError(error: String) {
                    ToastUtils.toastServerError(this@NavDrawerActivity)
                }

            }
        )

    }

    private fun onBack() {

        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    finish()
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
                }
            })

    }

    override fun onClick(view: View) {

        when (view.id) {

            //TODO Orders
            R.id.txtOrders -> {

            }

            //TODO Support
            R.id.txtSupport -> {

            }

            //TODO About
            R.id.txtAbout -> {
              /*  startActivity(Intent(this, AboutActivity::class.java))
                finish()
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)*/
            }

            //TODO Contact
            R.id.txtContact -> {

            }

            //TODO Upgrade
            R.id.txtUpgrade -> {

            }

            //TODO Logout
            R.id.txtLogout -> {
                finishAffinity()
            }

            R.id.imgCloseNav -> {
                finish()
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
            }

        }

    }
}