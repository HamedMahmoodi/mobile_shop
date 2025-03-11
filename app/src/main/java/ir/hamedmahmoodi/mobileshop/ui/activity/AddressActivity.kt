package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelAddressActivity
import ir.hamedmahmoodi.mobileshop.mvp.presenter.PresenterAddressActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewAddressActivity

class AddressActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterAddressActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewAddressActivity(this, this)
        setContentView(view.binding.root)

        presenter = PresenterAddressActivity(view, ModelAddressActivity(), this)
        presenter.onCreate()
    }

    override fun finished() {
        finish()
    }

    override fun onResume() {
        super.onResume()
        presenter.onStart()
    }
}