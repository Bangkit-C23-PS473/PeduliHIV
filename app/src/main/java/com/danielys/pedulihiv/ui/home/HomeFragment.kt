package com.danielys.pedulihiv.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.databinding.FragmentHomeBinding

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

    binding.btnKonsul.setOnClickListener {
        Toast.makeText(context, "coba pencet", Toast.LENGTH_SHORT).show()

        val customDialog = context?.let { it1 -> Dialog(it1) }
        customDialog?.setContentView(R.layout.dialog_consul)

        customDialog?.show()

        val weight = customDialog?.findViewById<TextView>(R.id.et_weight)
        val height = customDialog?.findViewById<TextView>(R.id.et_height)
        val age = customDialog?.findViewById<TextView>(R.id.et_age)
        val button = customDialog?.findViewById<Button>(R.id.btn_start_konsul)

        button?.setOnClickListener {

            customDialog.dismiss()
        }
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}