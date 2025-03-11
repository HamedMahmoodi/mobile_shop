package ir.hamedmahmoodi.mobileshop.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.adapter.recycler.ProductListRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductModel
import ir.hamedmahmoodi.mobileshop.databinding.ActivityFavoriteBinding

class ViewFavoriteActivity : FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding =
        ActivityFavoriteBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler(product: ArrayList<ProductModel>) {

        //TODO اینجا یه دکمه برای حذف علاقه مندی ها کم داره

        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewPastry.adapter =
            ProductListRecyclerAdapter(product, context)

        if (product.isEmpty())
            binding.txtNotFavorite.visibility = View.VISIBLE

    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun startGetData() {
        binding.allViews.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.allViews.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}