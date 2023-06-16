package com.danielys.pedulihiv.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielys.pedulihiv.data.response.DataItemPost
import com.danielys.pedulihiv.databinding.FragmentCommunityBinding
import com.danielys.pedulihiv.ui.community.CommunityViewModel
import com.danielys.pedulihiv.ui.community.PostAdapter

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val communityViewModel =
            ViewModelProvider(this).get(CommunityViewModel::class.java)

        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val root: View = binding.root


        communityViewModel.dataPost.observe(viewLifecycleOwner) {postResponse->
            if(postResponse.data?.size!! > 0){
                setPostData(postResponse.data as List<DataItemPost>)
            }
        }
        communityViewModel.getPost()
        return root
    }

    private fun setPostData(activities: List<DataItemPost>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvPost.layoutManager = layoutManager
        val adapter = PostAdapter(activities, requireContext())
        binding.rvPost.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}