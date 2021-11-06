package com.bytedance.tiktok.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bytedance.tiktok.adapter.WorkAdapter
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.databinding.FragmentWorkBinding

/**
 * 个人作品fragment
 */
class WorkFragment : Fragment() {

    private var _binding: FragmentWorkBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val workAdapter: WorkAdapter = WorkAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerview.adapter = workAdapter
        workAdapter.submitList(DataCreate.datas)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = WorkFragment()
    }
}