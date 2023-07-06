package com.sunul.sunul.presentation.spot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sunul.sunul.data.dto.SpotDTO
import com.sunul.sunul.databinding.JobsLayoutBinding
import com.sunul.sunul.util.callback.OnSpotItemClick

class SpotAdapter(private val onSpotItemClick: OnSpotItemClick) : ListAdapter<SpotDTO, SpotAdapter.SpotViewHolder>(SpotDiff) {
    lateinit var binding: JobsLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotViewHolder {
        binding = JobsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpotViewHolder, position: Int) {
        holder.onBind(getItem(position),position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    inner class SpotViewHolder(private val binding: JobsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: SpotDTO, position: Int){
            binding.root.setOnClickListener{
                onSpotItemClick.selectItem()
            }
            with(binding){
                with(item.data){
                    imageView.setImageResource(item.image)
                    textView1.text = title
                    textView6.text = des
                    textView7.text = loc
                    if(tag.isEmpty()){
                        textView2.isVisible = false
                        textView3.isVisible = false
                    }
                    else if(tag.size == 1){
                        textView2.isVisible = true
                        textView2.text= tag[0]
                        textView3.isVisible = false
                    }
                    else if(tag.size >= 2){
                        textView2.isVisible = true
                        textView3.isVisible = true
                        textView2.text= tag[0]
                        textView3.text= tag[1]
                    }
                }
            }
        }
    }
}

object SpotDiff : DiffUtil.ItemCallback<SpotDTO>() {
    override fun areItemsTheSame(oldItem: SpotDTO, newItem: SpotDTO): Boolean {
        return oldItem.data.sid == newItem.data.sid
    }

    override fun areContentsTheSame(oldItem: SpotDTO, newItem: SpotDTO): Boolean {
        return oldItem == newItem
    }
}