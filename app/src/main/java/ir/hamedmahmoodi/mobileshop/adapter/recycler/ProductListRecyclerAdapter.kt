package ir.hamedmahmoodi.mobileshop.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.adapter.recycler.diffUtil.RecyclerDiffUtil
import ir.hamedmahmoodi.mobileshop.androidWrapper.PicassoHandler
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.OthersUtilities
import ir.hamedmahmoodi.mobileshop.databinding.RecyclerItemListProductsBinding
import ir.hamedmahmoodi.mobileshop.ui.activity.DetailProductActivity

class ProductListRecyclerAdapter(
    private val products: ArrayList<ProductModel>,
    private val context: Context
) : RecyclerView.Adapter<ProductListRecyclerAdapter.ProductListViewHolder>(), Filterable {

    private val dataFull = ArrayList<ProductModel>()
    private val dataMain = ArrayList<ProductModel>()

    init {
        dataFull.addAll(products)
        dataMain.addAll(products)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ProductListViewHolder(RecyclerItemListProductsBinding.inflate(LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.setData(products[position])
    }

    inner class ProductListViewHolder(
        private val binding: RecyclerItemListProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: ProductModel) {

            binding.txtTitle.text = data.title
            binding.txtPriceMain.text = OthersUtilities.changePrice(data.price)

            if (data.thumbnail.isNotEmpty())
                PicassoHandler.setPicasso(binding.imgProduct, data.thumbnail)

            if (data.hasDiscount) {

                binding.txtPriceMain.paintFlags =
                    binding.txtPriceMain.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtPriceMain.setTextColor(Color.GRAY)

                binding.txtPriceOff.visibility = View.VISIBLE

                binding.txtPriceOff.text = OthersUtilities.changePrice(data.salePrice)
                binding.txtOff.text = data.discount

            } else {
                binding.off.visibility = View.GONE
                binding.txtPriceOff.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.ID, data.id)
                context.startActivity(intent)
            }

        }

    }

    override fun getFilter(): Filter =
        object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val data = ArrayList<ProductModel>()

                if (constraint.isNullOrEmpty())
                    data.addAll(dataFull)
                else {
                    val filter = constraint.toString().trim()
                    for (item in dataFull) {
                        if (item.title.contains(filter))
                            data.add(item)
                    }
                }

                products.clear()
                products.addAll(data)

                return FilterResults()

            }

            override fun publishResults(p0: CharSequence?, result: FilterResults?) {
                dataUpdate(products)
            }

        }

    private fun dataUpdate(newList: ArrayList<ProductModel>) {

        val diffCallback = RecyclerDiffUtil(dataMain, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataMain.clear()
        dataMain.addAll(newList)

        diffResult.dispatchUpdatesTo(this)

    }

}