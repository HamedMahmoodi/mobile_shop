package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import ir.hamedmahmoodi.mobileshop.adapter.viewPager.CustomSliderPagerAdapter
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelDetailProductActivity
import ir.hamedmahmoodi.mobileshop.mvp.pressenter.PresenterDetailProductActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewDetailProductActivity

class DetailProductActivity : AppCompatActivity(), ActivityUtils {

    companion object {
        const val ID = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewDetailProductActivity(this, this)
        setContentView(view.binding.root)

        val id = intent.getIntExtra(ID, 0)

        val presenter = PresenterDetailProductActivity(view, ModelDetailProductActivity(id), this)
        presenter.onCreate()


    }

    override fun setViewPagerFragment(viewPager: ViewPager2, data: ArrayList<String>) {

        viewPager.adapter =
            CustomSliderPagerAdapter(supportFragmentManager, lifecycle, data)

    }

    override fun finished() {
        finish()
    }
}