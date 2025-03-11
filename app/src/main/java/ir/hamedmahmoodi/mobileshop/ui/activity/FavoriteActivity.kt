package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelFavoriteActivity
import ir.hamedmahmoodi.mobileshop.mvp.presenter.PresenterFavoriteActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewFavoriteActivity

class FavoriteActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterFavoriteActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewFavoriteActivity(this, this)
        setContentView(view.binding.root)

        presenter = PresenterFavoriteActivity(view, ModelFavoriteActivity(), this)
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

}