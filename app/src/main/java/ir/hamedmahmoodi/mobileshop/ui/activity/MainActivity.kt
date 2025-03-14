package ir.hamedmahmoodi.mobileshop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.adapter.viewPager.CustomSliderPagerAdapter
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelMainActivity
import ir.hamedmahmoodi.mobileshop.mvp.presenter.PresenterMainActivity
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewMainActivity

class MainActivity : AppCompatActivity(), ActivityUtils {

    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ViewMainActivity(this, this)
        setContentView(view.binding.root)

        val model = ModelMainActivity()
        presenter = PresenterMainActivity(view, model)
        presenter.onCreate()

    }

    override fun setFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, fragment)
            .commit()

    }

    override fun setViewPagerFragment(viewPager: ViewPager2, data: ArrayList<String>) {

        viewPager.adapter =
            CustomSliderPagerAdapter(supportFragmentManager, lifecycle, data)

    }

}