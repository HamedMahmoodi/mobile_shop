package ir.hamedmahmoodi.mobileshop.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.databinding.ActivityMainBinding
import ir.hamedmahmoodi.mobileshop.ui.customView.bottomNav.ActiveFragment
import ir.hamedmahmoodi.mobileshop.ui.customView.bottomNav.FragmentType
import ir.hamedmahmoodi.mobileshop.ui.fragment.HomeFragment

class ViewMainActivity : FrameLayout, ActiveFragment {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils,
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityMainBinding.inflate(inflater)

    fun initialize() {
        activityUtils.setFragment(HomeFragment(context, activityUtils))
    }

    override fun setFragment(type: FragmentType) {

        val fragment = when (type) {
            FragmentType.HOME -> HomeFragment(context, activityUtils)
            //TODO CakeCatsFragment
                FragmentType.Phone ->HomeFragment(context, activityUtils) /*CakeCatsFragment(context)*/
            //TODO PastryCatsFragment
                FragmentType.Mobile -> HomeFragment(context, activityUtils) /*PastryCatsFragment(context)*/
            //TODO ProfileFragment
                FragmentType.PROFILE ->HomeFragment(context, activityUtils) /*ProfileFragment(context)*/
            //TODO SHOPPINGCARTFragment
            FragmentType.SHOPPINGCART ->HomeFragment(context, activityUtils) /*ShoppingCartFragment(context)*/

        }

        activityUtils.setFragment(fragment)

    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun initBottomNav() {
        binding.bottomNav.onClickHelper(this)
    }


}