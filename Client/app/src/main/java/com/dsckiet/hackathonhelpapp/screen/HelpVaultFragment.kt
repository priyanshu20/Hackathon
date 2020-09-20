package com.dsckiet.hackathonhelpapp.screen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.databinding.FragmentHelpVaultBinding


class HelpVaultFragment : Fragment() {
    lateinit var binding: FragmentHelpVaultBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_help_vault, container, false
            )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.back.setOnClickListener {
            navController.popBackStack()
        }
        val lat=arguments?.getDouble("lat")
        val lon=arguments?.getDouble("lon")
       Log.d("coord","$lat  $lon")
        binding.startVolunteer.setOnClickListener {
            Toast.makeText(requireContext(), "Opening Google Maps Application", Toast.LENGTH_SHORT)
                .show()
            val gmmIntentUri = Uri.parse("geo:$lat,$lon")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}