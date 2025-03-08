package ir.hamedmahmoodi.mobileshop.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.adapter.recycler.ProductListRecyclerAdapter
import ir.hamedmahmoodi.mobileshop.androidWrapper.ActivityUtils
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.AllProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ListProductsModel
import ir.hamedmahmoodi.mobileshop.databinding.ActivityListProductBinding

class ViewListProductActivity : FrameLayout {
    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils,
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding = ActivityListProductBinding.inflate(
        LayoutInflater.from(context)
    )

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun setData(data: ListProductsModel) {

        binding.recyclerViewProduct.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = ProductListRecyclerAdapter(data.category.products, context)
        binding.recyclerViewProduct.adapter = adapter

        binding.txtTitle.text = data.category.title

        binding.edtSearch.getEditText().doOnTextChanged { text, _, _, _ ->
            adapter.filter.filter(text)
        }

    }

    fun setData2(data: AllProductsModel, title: String) {

        binding.recyclerViewProduct.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = ProductListRecyclerAdapter(data.products, context)
        binding.recyclerViewProduct.adapter = adapter

        binding.edtSearch.getEditText().doOnTextChanged { text, _, _, _ ->
            adapter.filter.filter(text)
        }

        binding.txtTitle.text = title

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