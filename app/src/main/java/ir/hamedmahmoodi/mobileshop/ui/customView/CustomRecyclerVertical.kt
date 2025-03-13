package ir.hamedmahmoodi.mobileshop.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.hamedmahmoodi.mobileshop.databinding.CustomMainRecyclerVerticalBinding

class CustomRecyclerVertical(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding: CustomMainRecyclerVerticalBinding

    init {

        binding = CustomMainRecyclerVerticalBinding.inflate(
            LayoutInflater.from(context)
        )

        addView(binding.root)

    }

    fun getRecycler() = binding.recyclerView

}