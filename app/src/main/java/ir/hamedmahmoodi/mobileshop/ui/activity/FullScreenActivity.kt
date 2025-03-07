package ir.hamedmahmoodi.mobileshop.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.data.local.preferences.SecureSharePref
import ir.hamedmahmoodi.mobileshop.data.local.preferences.SharedPrefKey
import ir.hamedmahmoodi.mobileshop.databinding.ActivityFullScreenBinding

class FullScreenActivity : AppCompatActivity(), ActivityUtils {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStatus()

        val loginState = SecureSharePref.getSharedPref(this)

        Handler(Looper.getMainLooper()).postDelayed({
            if (loginState.getBoolean(SharedPrefKey.LOGIN_STATE_KEY, false))
                startActivity(Intent(this@FullScreenActivity, MainActivity::class.java))
            else
                startActivity(Intent(this@FullScreenActivity,LoginActivity::class.java))
            finish()
        },2500
        )

    }

    private fun hideStatus() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val attrib = window.attributes
            attrib.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        } else {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

        }

    }

}