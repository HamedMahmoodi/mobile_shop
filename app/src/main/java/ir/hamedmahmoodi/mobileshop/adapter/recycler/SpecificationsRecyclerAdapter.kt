package ir.hamedmahmoodi.mobileshop.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.R
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.Specifications
import ir.hamedmahmoodi.mobileshop.databinding.RecyclerItemListProductSpecificationsBinding

class SpecificationsRecyclerAdapter(
    private val specifications: ArrayList<Specifications>
) : RecyclerView.Adapter<SpecificationsRecyclerAdapter.SpecificationsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SpecificationsViewHolder(
        RecyclerItemListProductSpecificationsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = specifications.size

    override fun onBindViewHolder(holder: SpecificationsViewHolder, position: Int) {
        holder.setData(specifications[position])
    }

    inner class SpecificationsViewHolder(
        private val binding: RecyclerItemListProductSpecificationsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Specifications) {

            binding.txtSpecifications.text = data.specification
            binding.txtAmount.text = data.amount

            if (adapterPosition % 2 != 0)
                binding.root.setBackgroundResource(R.color.white_color200)

        }

    }

}