package com.dsckiet.hackathonhelpapp.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.dsckiet.hackathonhelpapp.Constants
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.databinding.FragmentGetVolunteerBinding
import com.dsckiet.hackathonhelpapp.model.AddHelpBody
import com.dsckiet.hackathonhelpapp.model.AddHelpResponse
import com.dsckiet.hackathonhelpapp.network.AppNetwork
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GetVolunteerFragment : Fragment() {

    lateinit var binding: FragmentGetVolunteerBinding
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_get_volunteer, container, false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.back.setOnClickListener {
            navController.popBackStack()
        }
        binding.submit.setOnClickListener {
            if (binding.editDesc.text.isNullOrBlank() || binding.editVolNeeded.text.isNullOrBlank())
                Snackbar.make(
                    binding.coordinatorLayout,
                    "Fill the entries first",
                    Snackbar.LENGTH_SHORT
                ).show()
            else {
                val addHelpBody = AddHelpBody(
                    description = binding.editDesc.text.toString(),
                    forKids = false.toString(),
                    latitude = Constants.latitude.toString(),
                   longitude =  Constants.longitude.toString(),
                   tag =  "general",
                    vCounter = binding.editVolNeeded.toString()
                )
                val retrofitService = AppNetwork.getClient(requireContext())
                    .askHelp("priyanshus.edu@gmail.com", addHelpBody)
                retrofitService.enqueue(
                    object : Callback<AddHelpResponse> {
                        override fun onFailure(call: Call<AddHelpResponse>, t: Throwable) {
                            Log.d("failure",t.message.toString())
                        }

                        override fun onResponse(
                            call: Call<AddHelpResponse>,
                            response: Response<AddHelpResponse>
                        ) {
                           Log.d("success",response.toString())
                            if(response.code()==200)
                            {
                                Snackbar.make(binding.coordinatorLayout,"Volunteer Help Requested",Snackbar.LENGTH_SHORT).show()
                            }

                        }

                    }
                )
            }
        }

    }

}