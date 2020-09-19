package com.dsckiet.hackathonhelpapp.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.databinding.FragmentVolunteerBinding


class VolunteerFragment : Fragment() {
    private lateinit var binding: FragmentVolunteerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_volunteer, container, false
            )
        return binding.root
    }


}