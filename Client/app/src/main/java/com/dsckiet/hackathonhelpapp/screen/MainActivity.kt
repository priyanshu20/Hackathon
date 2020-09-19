package com.dsckiet.hackathonhelpapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var homefragment: HomeFragment
    lateinit var volFragment: VolunteerFragment
    lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bottomNav()

    }

    fun bottomNav() {

        binding.bottomNav.setOnNavigationItemReselectedListener { item ->

            when (item.itemId) {

                R.id.home -> {
                    homefragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()                                            //shows Home fragment
                        .replace(R.id.fragment, homefragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.list -> {
                    volFragment = VolunteerFragment()
                    supportFragmentManager
                        .beginTransaction()                                            //shows List fragment
                        .replace(R.id.fragment, volFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.Settings -> {

                    profileFragment = ProfileFragment()
                    supportFragmentManager
                        .beginTransaction()                                            //shows Profile fragment
                        .replace(R.id.fragment, profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }
    }
}