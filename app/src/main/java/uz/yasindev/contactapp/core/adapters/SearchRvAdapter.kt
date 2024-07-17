package uz.yasindev.contactapp.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.databinding.ItemContactBinding

class SearchRvAdapter : RecyclerView.Adapter<SearchRvAdapter.SearchViewHolder>() {

    private val data = ArrayList<RvContactModel>()
    var itemClickListener: ((rvModel: RvContactModel) -> Unit)? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<RvContactModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(rvModel: RvContactModel) {
            binding.userImage.load(rvModel.image)
            binding.userNameTv.text = rvModel.contactName
            binding.userNumberTv.text = rvModel.phoneNumber

            itemView.setOnClickListener {
                itemClickListener?.invoke(rvModel)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SearchRvAdapter.SearchViewHolder {
        return SearchViewHolder(
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchRvAdapter.SearchViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}