package com.example.usupbekov_adik_4m.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.usupbekov_adik_4m.App
import com.example.usupbekov_adik_4m.model.Task
import com.example.usupbekov_adik_4m.databinding.FragmentTaskBinding

class TaskFragment() : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTask.setOnClickListener {
            save()
        }
    }

    private fun update() {
        task?.title = binding.etTitle.text.toString()
        task?.desc = binding.etDesc.text.toString()
        task?.let { App.db.taskDao().update(it) }
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString(),
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

    companion object {
        const val ASL = "LIST"
    }
}

