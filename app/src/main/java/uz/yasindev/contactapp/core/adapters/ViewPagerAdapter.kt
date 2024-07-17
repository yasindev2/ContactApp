package uz.yasindev.contactapp.core.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.yasindev.contactapp.core.models.VpModel
import uz.yasindev.contactapp.databinding.ItemViewpagerBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private val data = ArrayList<VpModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<VpModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(vpModel: VpModel) {
            binding.imageView.setImageResource(vpModel.image)
            binding.titleTv.text = vpModel.title
            binding.description.text = vpModel.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

}