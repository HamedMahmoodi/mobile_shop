package ir.hamedmahmoodi.mobileshop.adapter.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.androidWrapper.PicassoHandler
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.CategoriesModel
import ir.hamedmahmoodi.mobileshop.databinding.RecyclerItemMainCategoriesBinding
import ir.hamedmahmoodi.mobileshop.ui.activity.ListProductActivity

class CategoriesRecyclerAdapter(
    private val cats: ArrayList<CategoriesModel>,
    private val context: Context
) : RecyclerView.Adapter<CategoriesRecyclerAdapter.ProductCategoriesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ProductCategoriesViewHolder(
        RecyclerItemMainCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: ProductCategoriesViewHolder, position: Int) {
        holder.setData(cats[position])
    }

    inner class ProductCategoriesViewHolder(
        private val binding: RecyclerItemMainCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: CategoriesModel) {

            binding.txtCategory.text = data.title

            if (data.thumbnail.isNotEmpty())
                PicassoHandler.setPicassoCats(binding.imgCategory, data.thumbnail)

            binding.root.setOnClickListener {
                val intent = Intent(context, ListProductActivity::class.java)
                intent.putExtra(ListProductActivity.ID, data.id)
                context.startActivity(intent)
            }

        }

    }
}