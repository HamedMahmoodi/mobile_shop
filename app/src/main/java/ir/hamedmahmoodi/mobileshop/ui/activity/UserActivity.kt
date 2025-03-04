package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelUserActivity
import ir.hamedmahmoodi.mobileshop.mvp.pressenter.PresenterUserActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewUserActivity

class UserActivity : AppCompatActivity(), ActivityUtils {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewUserActivity(this,this)
        setContentView(view.binding.root)

        val presenter= PresenterUserActivity(view, ModelUserActivity(this),this)
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}