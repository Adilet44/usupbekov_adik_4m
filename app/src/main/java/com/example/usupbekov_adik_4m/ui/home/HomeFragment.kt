package com.example.usupbekov_adik_4m.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.usupbekov_adik_4m.App
import com.example.usupbekov_adik_4m.R
import com.example.usupbekov_adik_4m.databinding.FragmentHomeBinding
import com.example.usupbekov_adik_4m.model.Task
import com.example.usupbekov_adik_4m.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter (this::onLongClick , this::onClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.adapter = adapter
        setData()
        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun setData() {
        val list = App.db.taskDao().getAll()
        adapter.addTask(list)
    }
    private fun onClick(task: Task) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Deleting the task")
        alertDialog.setMessage("Are you sure you want to delete this task")
        alertDialog.setNegativeButton("No") { dialog, _ -> dialog?.cancel() }
        alertDialog.setPositiveButton("Yes") { _, _ ->
            App.db.taskDao().delete(task)
            setData()
        }
        alertDialog.create().show()
    }
}