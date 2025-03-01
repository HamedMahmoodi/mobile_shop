package ir.hamedmahmoodi.mobileshop.ui.customView.bottomNav

enum class FragmentType {
    HOME, Phone, Mobile, PROFILE, SHOPPINGCART
}

interface ActiveFragment {

    fun setFragment(type: FragmentType)

}