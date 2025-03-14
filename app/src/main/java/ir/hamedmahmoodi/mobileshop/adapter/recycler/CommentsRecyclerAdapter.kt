package ir.hamedmahmoodi.mobileshop.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.hamedmahmoodi.mobileshop.data.remote.dataModel.Comments
import ir.hamedmahmoodi.mobileshop.databinding.RecyclerItemCommentsBinding

class CommentsRecyclerAdapter(
    private val comments: ArrayList<Comments>
) : RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CommentsViewHolder(
        RecyclerItemCommentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.setData(comments[position])
    }

    inner class CommentsViewHolder(
        private val binding: RecyclerItemCommentsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Comments) {

            binding.txtName.text = data.name
            binding.txtRate.text = data.rate.toString()
            binding.txtContent.text = data.body

        }

    }

}