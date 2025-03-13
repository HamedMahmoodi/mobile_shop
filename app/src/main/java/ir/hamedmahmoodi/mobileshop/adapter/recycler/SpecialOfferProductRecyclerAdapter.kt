package ir.hamedmahmoodi.mobileshop.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.androidWrapper.PicassoHandler
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.ProductsModel
import ir.hamedmahmoodi.mobileshop.data.remote.ext.OthersUtilities
import ir.hamedmahmoodi.mobileshop.databinding.RecyclerItemMainVerticalBinding
import ir.hamedmahmoodi.mobileshop.ui.activity.DetailProductActivity
import ir.hamedmahmoodi.mobileshop.ui.activity.ListProductActivity

class SpecialOfferProductRecyclerAdapter(
    private val products: ArrayList<ProductsModel>,
    private val context: Context
) : RecyclerView.Adapter<SpecialOfferProductRecyclerAdapter.SpecialOfferProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SpecialOfferProductViewHolder(
        RecyclerItemMainVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: SpecialOfferProductViewHolder, position: Int) {
        holder.setData(products[position], position)
    }

    inner class SpecialOfferProductViewHolder(
        private val binding: RecyclerItemMainVerticalBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: ProductsModel, position: Int) {

            if (position == 0) {

                binding.root.visibility = View.INVISIBLE

            } else {

                binding.root.visibility = View.VISIBLE
                binding.txtProductName.text = data.title
                binding.txtMainPrice.text = OthersUtilities.changePrice(data.price)

                if (data.hasDiscount) {

                    binding.txtMainPrice.paintFlags =
                        binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.txtMainPrice.setTextColor(Color.GRAY)

                    binding.txtOffPrice.text =
                        OthersUtilities.changePrice(data.salePrice)
                    binding.txtOff.text = data.discount

                } else
                    binding.off.visibility = View.GONE

                if (data.thumbnail.isNotEmpty())
                    PicassoHandler.setPicasso(binding.imgProduct, data.thumbnail)

                if (position == products.lastIndex) {

                    binding.allViews.visibility = View.INVISIBLE
                    binding.others.visibility = View.VISIBLE

                    binding.root.setOnClickListener {
                        val intent = Intent(context, ListProductActivity::class.java)
                        intent.putExtra(ListProductActivity.TYPE, ListProductActivity.SPECIAL_OFFER)
                        context.startActivity(intent)
                    }

                }

                binding.root.setOnClickListener {
                    val intent = Intent(context, DetailProductActivity::class.java)
                    intent.putExtra(DetailProductActivity.ID, data.id)
                    context.startActivity(intent)
                }

            }

        }

    }

}