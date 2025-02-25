package ir.hamedmahmoodi.mobileshop.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelHomeFragment
import ir.hamedmahmoodi.mobileshop.mvp.pressenter.PresenterHomeFragment
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewHomeFragment

class HomeFragment(
    private val mContext: Context,
    private val activityUtils: ActivityUtils
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = ViewHomeFragment(mContext, activityUtils)
        val presenter = PresenterHomeFragment(view, ModelHomeFragment(), mContext)
        presenter.onCreate()
        return view.binding.root

    }

}