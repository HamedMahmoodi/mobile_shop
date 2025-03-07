package ir.hamedmahmoodi.mobileshop.ui.customView.bottomNav

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.databinding.CustomBottomNavigationBinding

class CustomBottomNavigation(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private val binding: CustomBottomNavigationBinding

    init {

        binding = CustomBottomNavigationBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        binding.txtShoppingCount.text = "0"

    }

    fun onClickHelper(activeFragment: ActiveFragment) {

        binding.homes.setOnClickListener {
            activeHome()
            activeFragment.setFragment(FragmentType.HOME)
        }

        binding.mobile.setOnClickListener {
            activeMobile()
            activeFragment.setFragment(FragmentType.Mobile)
        }

        binding.product.setOnClickListener {
            activeProduct()
            activeFragment.setFragment(FragmentType.Product)
        }

        binding.profile.setOnClickListener {
            activeProfile()
            activeFragment.setFragment(FragmentType.PROFILE)
        }

        binding.shoppingCart.setOnClickListener {
            activeShoppingCart()
            activeFragment.setFragment(FragmentType.SHOPPINGCART)
        }

    }

    private fun activeHome() {

        binding.homes.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.mobile.background = null
        binding.product.background = null
        binding.profile.background = null
        binding.shoppingCart.background = null

    }

    private fun activeMobile() {

        binding.mobile.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.product.background = null
        binding.profile.background = null
        binding.shoppingCart.background = null

    }

    private fun activeProduct() {

        binding.product.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.mobile.background = null
        binding.profile.background = null
        binding.shoppingCart.background = null

    }

    private fun activeProfile() {

        binding.profile.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.mobile.background = null
        binding.product.background = null
        binding.shoppingCart.background = null

    }

    private fun activeShoppingCart() {

        binding.shoppingCart.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.mobile.background = null
        binding.product.background = null
        binding.profile.background = null

    }

    fun changeCount(count: String) {
        binding.off.visibility = VISIBLE
        binding.txtShoppingCount.text = count
    }

}