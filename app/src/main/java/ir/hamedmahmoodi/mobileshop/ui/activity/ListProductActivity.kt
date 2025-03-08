package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelListProductActivity
import ir.hamedmahmoodi.mobileshop.mvp.pressenter.PresenterListProductActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewListProductActivity

class ListProductActivity : AppCompatActivity(), ActivityUtils {

    companion object {
        const val ID = "ID"
        const val TYPE = "TYPE"
        const val NEW = "NEW"
        const val SPECIAL_OFFER = "SPECIAL_OFFER"
        const val RATE = "RATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewListProductActivity(this, this)
        setContentView(view.binding.root)

        val id = intent.getIntExtra(ID, 0)
        val type = intent.getStringExtra(TYPE) ?: ""

        val presenter = PresenterListProductActivity(view, ModelListProductActivity(id, type),
            this
        )
        presenter.onCreate()

    }

    override fun finished() {
        finish()
    }

}