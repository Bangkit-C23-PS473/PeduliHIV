package com.danielys.pedulihiv.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielys.pedulihiv.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

private var _binding: FragmentCalendarBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val communityViewModel =
            ViewModelProvider(this).get(CalendarViewModel::class.java)

    _binding = FragmentCalendarBinding.inflate(inflater, container, false)
    val root: View = binding.root

    binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
        Toast.makeText(context, "$dayOfMonth:$month:$year", Toast.LENGTH_SHORT).show()
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}