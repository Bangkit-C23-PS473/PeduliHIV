package com.danielys.pedulihiv.ui.consultation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielys.pedulihiv.data.response.ConsultationsItem
import com.danielys.pedulihiv.data.response.DataItem
import com.danielys.pedulihiv.databinding.FragmentConsultationBinding
import com.danielys.pedulihiv.ui.home.ActivitiesAdapter
import com.danielys.pedulihiv.ui.listdoctor.ListDoctorActivity

class ConsultationFragment : Fragment() {

    private var _binding: FragmentConsultationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val consultationViewModel =
            ViewModelProvider(this).get(ConsultationViewModel::class.java)

        _binding = FragmentConsultationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        consultationViewModel.dataConsultation.observe(requireActivity()){consultationResponse->
            if(consultationResponse.message == "User's consultations retrieved successfully" && consultationResponse.consultations!!.isNotEmpty()){
                setConsultationData(consultationResponse.consultations as List<ConsultationsItem>)
            }
        }
        consultationViewModel.getConsultation()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(requireContext(),ListDoctorActivity::class.java)
            context?.startActivity(intent)
        }

        return root
    }

    private fun setConsultationData(consults: List<ConsultationsItem>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvConsul.layoutManager = layoutManager
        val adapter = ConsultationsAdapter(consults, requireContext())
        binding.rvConsul.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}