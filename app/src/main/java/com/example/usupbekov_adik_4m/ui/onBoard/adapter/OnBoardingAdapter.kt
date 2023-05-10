package com.example.usupbekov_adik_4m.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.usupbekov_adik_4m.databinding.ItemOnboardBinding
import com.example.usupbekov_adik_4m.model.onBoard
import com.example.usupbekov_adik_4m.utils.loadImage

class OnBoardingAdapter(private val onClick: (onBoard) -> Unit) :
    Adapter<OnBoardingAdapter.onBoardingViewHolder>() {

    private val data = arrayListOf(
        onBoard(
            title = "title 1",
            description = "desc 1",
            image = "https://www.cflowapps.com/wp-content/uploads/2021/12/diffnce_job_task_process.jpeg"
        ),
        onBoard(
            title = "title 2",
            description = "desc 2",
            image = "https://merriam-webster.com/assets/mw/images/article/art-wap-article-main/can-task-be-a-verb-5813-7075c71d8cf734c3c83e9edf76bc66fb@1x.jpg"
        ),
        onBoard(
            title = "title 3",
            description = "desc 3",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6kcS4NLkJI0C4npOcIooYDxM9snr6zPn4wA&usqp=CAU"
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onBoardingViewHolder {
        return onBoardingViewHolder(
            ItemOnboardBinding.inflate
                (
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: onBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class onBoardingViewHolder(private val binding: ItemOnboardBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoard: onBoard) {
            binding.btnStart.setOnClickListener {
                onClick(onBoard)
            }
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.description
            binding.ivOnboard.loadImage(onBoard.image)
        }

    }
}