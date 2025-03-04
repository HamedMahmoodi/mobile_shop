package ir.hamedmahmoodi.mobileshop.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.hamedmahmoodi.mobileshop.mvp.model.ModelProfileFragment
import ir.hamedmahmoodi.mobileshop.mvp.pressenter.PresenterProfileFragment
import ir.hamedmahmoodi.mobileshop.mvp.view.ViewProfileFragment

class ProfileFragment(private val mContext: Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val view = ViewProfileFragment(mContext)
        val presenter=PresenterProfileFragment(view, ModelProfileFragment(mContext),mContext)
        presenter.onCreate()
        return view.binding.root

    }

}