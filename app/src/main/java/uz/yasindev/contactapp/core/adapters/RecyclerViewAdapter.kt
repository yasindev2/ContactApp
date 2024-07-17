package uz.yasindev.contactapp.core.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.yasindev.contactapp.core.models.RvContactModel
import uz.yasindev.contactapp.databinding.ItemContactBinding

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val data = ArrayList<RvContactModel>()
    var onClickListener: ((rvModel: RvContactModel) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<RvContactModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addUser(rvModel: RvContactModel) {
        this.data.add(rvModel)
        notifyItemInserted(data.size)
    }

    fun deleteUser(id: Int) {
        this.data.removeAt(id)
        notifyItemRemoved(id)
        notifyItemRangeChanged(id,data.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteAllUsers() {
        this.data.clear()
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindData(rvModel: RvContactModel) {
            Log.d("TAG222", "bindData: $rvModel")
            Log.d("TAG222", "bindData: ${rvModel.image}")
            binding.userImage.load(rvModel.image)
            binding.userNameTv.text = rvModel.contactName
            binding.userNumberTv.text = rvModel.phoneNumber

            itemView.setOnClickListener {
                onClickListener?.invoke(rvModel)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

}