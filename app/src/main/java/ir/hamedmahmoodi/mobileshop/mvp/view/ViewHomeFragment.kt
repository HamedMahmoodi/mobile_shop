package ir.hamedmahmoodi.mobileshop.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.adapter.recycler.NewProductRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.adapter.recycler.SpecialOfferProductRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.adapter.recycler.TopProductRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.androidWrapper.PicassoHandler
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.RequestMain
import ir.hamedmahmoodi.mobileshop.databinding.FragmentHomeBinding
import ir.hamedmahmoodi.mobileshop.ui.activity.ListProductActivity

class ViewHomeFragment : FrameLayout {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils,
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    val binding =
        FragmentHomeBinding.inflate(LayoutInflater.from(context))

    fun startGetData() {
        binding.content.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.content.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun initialized(data: RequestMain) {

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL
        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

        binding.newProductRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        binding.newProductRecycler.getRecycler().adapter =
            NewProductRecyclerAdapter(data.pastries[0].pastries, context)

        binding.specialOfferProductRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        val specialOfferData = data.pastries[1].pastries
        specialOfferData.add(
            0,
            ProductsModel(
                0,
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )
        specialOfferData.add(
            ProductsModel(
                0,
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )
        binding.specialOfferProductRecycler.getRecycler().adapter =
            SpecialOfferProductRecyclerAdapter(specialOfferData, context)

        binding.topProductRecycler.getRecycler().layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.topProductRecycler.getRecycler().adapter =
            TopProductRecyclerAdapter(data.pastries[2].pastries, context)

        if (data.banners.isNotEmpty() && data.banners[0].large.isNotEmpty())
            PicassoHandler.setPicassoBanner(binding.imgBanner, data.banners[0].large)

        binding.newProductRecycler.getAll().setOnClickListener {
            val intent = Intent(context, ListProductActivity::class.java)
            intent.putExtra(ListProductActivity.TYPE, ListProductActivity.NEW)
            context.startActivity(intent)
        }

        binding.topProductRecycler.getAll().setOnClickListener {
            val intent = Intent(context, ListProductActivity::class.java)
            intent.putExtra(ListProductActivity.TYPE, ListProductActivity.RATE)
            context.startActivity(intent)
        }

    }

}