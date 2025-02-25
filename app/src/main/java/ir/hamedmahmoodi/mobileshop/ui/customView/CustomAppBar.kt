package ir.hamedmahmoodi.mobileshop.ui.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.databinding.CustomAppBarBinding
import ir.hamedmahmoodi.mobileshop.ui.activity.NavDrawerActivity

class CustomAppBar(
    context: Context,
    attrs: AttributeSet,
) : FrameLayout(context, attrs) {

    private val binding = CustomAppBarBinding.inflate(LayoutInflater.from(context))

    init {

        addView(binding.root)

        initialize(attrs)

    }

    private fun initialize(attrs: AttributeSet) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomAppBar)

        val isBack = typeArray.getBoolean(R.styleable.CustomAppBar_backIcon, false)

        if (isBack) {
            binding.imgBack.visibility = View.VISIBLE
            binding.imgShopping.visibility = View.VISIBLE
            binding.imgAlert.visibility = View.INVISIBLE
        }

        typeArray.recycle()

    }

    fun getBackIcon() = binding.imgBack

    fun countShow() {
        binding.countGroup.visibility = VISIBLE
    }

    fun countHide() {
        binding.countGroup.visibility = INVISIBLE
    }

    fun setCount(count: String) {
        binding.txtAlertCount.text = count
    }

    fun showNavDrawer(context: Context) {

        binding.imgMenu.setOnClickListener {
            context.startActivity(Intent(context, NavDrawerActivity::class.java))
        }

    }

}