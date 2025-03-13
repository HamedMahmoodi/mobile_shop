package ir.hamedmahmoodi.mobileshop.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.databinding.CustomMainRecyclerHorizontalBinding

class CustomRecyclerHorizontal(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding: CustomMainRecyclerHorizontalBinding

    init {

        binding = CustomMainRecyclerHorizontalBinding.inflate(LayoutInflater.from(context))

        addView(binding.root)

        initialized(attrs)

    }

    fun getRecycler() = binding.recyclerView

    fun getAll() = binding.txtAll

    private fun initialized(attrs: AttributeSet){

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomRecyclerHorizontal)
        val title = typeArray.getString(R.styleable.CustomRecyclerHorizontal_title)
        binding.txtTitle.text = title
        typeArray.recycle()

    }

}