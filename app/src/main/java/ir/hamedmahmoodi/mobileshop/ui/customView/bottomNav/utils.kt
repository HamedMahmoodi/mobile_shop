package ir.hamedmahmoodi.mobileshop.ui.customView.bottomNav

enum class FragmentType {
    HOME, Mobile, Product, PROFILE, SHOPPINGCART
}

interface ActiveFragment {

    fun setFragment(type: FragmentType)

}