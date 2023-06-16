package com.danielys.pedulihiv.ui.calendar

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielys.pedulihiv.data.UserPreferences
import com.danielys.pedulihiv.databinding.FragmentAccountBinding
import com.danielys.pedulihiv.ui.PrefViewModelProvider
import com.danielys.pedulihiv.ui.account.AccountViewModel
import com.danielys.pedulihiv.ui.login.LoginViewModel


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val pref = UserPreferences.getInstance(requireContext().dataStore)
        val accountViewModel = ViewModelProvider(this, PrefViewModelProvider(pref)).get(
            AccountViewModel::class.java
        )

        binding.btnLogout.setOnClickListener {
            accountViewModel.unsetData()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}