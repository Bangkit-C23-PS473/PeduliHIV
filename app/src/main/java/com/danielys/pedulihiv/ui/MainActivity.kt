package com.danielys.pedulihiv.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.danielys.pedulihiv.R
import com.danielys.pedulihiv.databinding.ActivityMainBinding
import com.danielys.pedulihiv.ui.calendar.AccountFragment
import com.danielys.pedulihiv.ui.calendar.CalendarFragment
import com.danielys.pedulihiv.ui.calendar.CommunityFragment
import com.danielys.pedulihiv.ui.consultation.ConsultationFragment
import com.danielys.pedulihiv.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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