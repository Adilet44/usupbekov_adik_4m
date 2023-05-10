package com.example.usupbekov_adik_4m.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.usupbekov_adik_4m.App
import com.example.usupbekov_adik_4m.model.Task
import com.example.usupbekov_adik_4m.databinding.FragmentTaskBinding

class TaskFragment() : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null
    private lateinit var navArgs: TaskFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            navArgs = TaskFragmentArgs.fromBundle(it)
            task = navArgs.task
        }

        if (task != null) {
            binding.etTitle.setText(task?.title)
            binding.etDesc.setText(task?.desc)
            binding.btnTask.text = "Update"
        } else {
            binding.btnTask.text = "Save"
        }

            save()
    }

    private fun save() {
        binding.btnTask.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString(),
            )

            if (data.title!!.isEmpty() || data.desc!!.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Title and desc cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (task != null) {
                task!!.title = data.title
                task!!.desc = data.desc
                App.db.taskDao().update(task!!)
            } else {
                task = Task(title = data.title, desc = data.desc)
                App.db.taskDao().insert(task!!)
            }
            findNavController().navigateUp()
        }
    }

    companion object {
        const val ASL = "LIST"
    }
}

