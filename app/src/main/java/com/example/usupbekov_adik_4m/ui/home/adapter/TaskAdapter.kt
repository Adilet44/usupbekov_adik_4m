package com.example.usupbekov_adik_4m.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.usupbekov_adik_4m.databinding.ItemTaskBinding
import com.example.usupbekov_adik_4m.model.Task

class TaskAdapter(
    private val onLongClick: (Task) -> Unit,
    private val onClick: (Task) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val data: ArrayList<Task> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(task: List<Task>) {
        data.clear()
        data.addAll(0, task)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate
                (
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
            itemView.setOnClickListener {
                onClick(task)
            }

        }
    }
}


