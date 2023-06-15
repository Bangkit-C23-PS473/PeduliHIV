package com.danielys.pedulihiv.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.data.Global
import com.danielys.pedulihiv.data.UserPreferences
import com.danielys.pedulihiv.databinding.ActivityMainBinding
import com.danielys.pedulihiv.ui.calendar.AccountFragment
import com.danielys.pedulihiv.ui.calendar.CalendarFragment
import com.danielys.pedulihiv.ui.calendar.CommunityFragment
import com.danielys.pedulihiv.ui.consultation.ConsultationFragment
import com.danielys.pedulihiv.ui.home.HomeFragment
import com.danielys.pedulihiv.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = UserPreferences.getInstance(dataStore)
        mainViewModel = ViewModelProvider(this, MainViewModelProvider(pref)).get(
            MainViewModel::class.java
        )

        mainViewModel.getUsername().observe(this) { username ->
            if (username != "") {
                mainViewModel.setGlobal()
            }  else {
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }


        val navView: BottomNavigationView = binding.navView
        navView.selectedItemId = R.id.menu_home
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)

        val firstFragment = ConsultationFragment()
        val secondFragment = CalendarFragment()
        val thirdFragment = HomeFragment()
        val fourFragment = CommunityFragment()
        val fiveFragment = AccountFragment()

        setCurrentFragment(thirdFragment)


        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_consul -> setCurrentFragment(firstFragment)
                R.id.menu_activity -> setCurrentFragment(secondFragment)
                R.id.menu_home -> setCurrentFragment(thirdFragment)
                R.id.menu_community -> setCurrentFragment(fourFragment)
                R.id.menu_akun -> setCurrentFragment(fiveFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_main,fragment)
            commit()
        }
}