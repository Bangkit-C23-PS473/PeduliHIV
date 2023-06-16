package com.danielys.pedulihiv.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.databinding.FragmentHomeBinding
import com.danielys.pedulihiv.ui.addpost.AddPostActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        homeViewModel.dataMotivation.observe(viewLifecycleOwner) { motivationResponse ->
            if (motivationResponse.message == "Motivation retrieved") {
                val dataMotivation = motivationResponse.data
                with(dataMotivation) {
                    Glide.with(requireContext())
                        .load(this?.photo)
                        .into(binding.ivMotivation)
                    binding.tvMotivation.text = this?.text
                }
            }
            else{
                binding.btnKonsul.visibility = View.VISIBLE
                binding.tvKonsul.visibility = View.VISIBLE
                binding.rvActivities.visibility = View.GONE
            }
        }
        homeViewModel.getMotivation()

        CoroutineScope(Dispatchers.Main).launch {
            delay(1)
            binding.tvWelcome.text = "Halo, ${Global.user.name}"
            homeViewModel.getActivities(Global.user.username)
        }

        homeViewModel.dataActivities.observe(viewLifecycleOwner) { activitiesResponse ->
            val listActivities = activitiesResponse.data
            if (!listActivities.isNullOrEmpty()) {
                setActivitiesData(listActivities as List<DataItem>)
            }

        }

        binding.btnKonsul.setOnClickListener {
            Toast.makeText(context, Global.user?.username.toString(), Toast.LENGTH_SHORT).show()
            val customDialog = context?.let { it1 -> Dialog(it1) }
            customDialog?.setContentView(R.layout.dialog_consul)

            customDialog?.show()

            val weight = customDialog?.findViewById<TextView>(R.id.et_weight)
            val height = customDialog?.findViewById<TextView>(R.id.et_height)
            val age = customDialog?.findViewById<TextView>(R.id.et_age)
            val button = customDialog?.findViewById<Button>(R.id.btn_start_konsul)

            button?.setOnClickListener {
                val intent = Intent(context, AddPostActivity::class.java)
                startActivity(intent)

                customDialog.dismiss()
            }
        }
        return root
    }

    private fun setActivitiesData(activities: List<DataItem>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvActivities.layoutManager = layoutManager
        val adapter = ActivitiesAdapter(activities, requireContext())
        binding.rvActivities.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}