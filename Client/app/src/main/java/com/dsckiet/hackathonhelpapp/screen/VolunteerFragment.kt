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
import com.dsckiet.hackathonhelpapp.R
import com.dsckiet.hackathonhelpapp.adapter.GeneralHelpAdapter
import com.dsckiet.hackathonhelpapp.databinding.FragmentVolunteerBinding
import com.dsckiet.hackathonhelpapp.model.GeneralHelpResponse
import com.dsckiet.hackathonhelpapp.network.AppNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VolunteerFragment : Fragment() {
    private lateinit var binding: FragmentVolunteerBinding
    private lateinit var navController: NavController

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.getVolunteerCard.setOnClickListener {
            navController.navigate(R.id.action_volunteerFragment_to_getVolunteerFragment2)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val retrofitService=AppNetwork.getClient(requireContext()).getGeneralHelp("priyanshus.edu@gmail.com")
        retrofitService.enqueue(object: Callback<GeneralHelpResponse>{
            override fun onFailure(call: Call<GeneralHelpResponse>, t: Throwable) {
                Log.d("error",t.message.toString())
            }

            override fun onResponse(
                call: Call<GeneralHelpResponse>,
                response: Response<GeneralHelpResponse>
            ) {
                Log.d("success",response.toString())
                if(response.code()==200)
                {
                    binding.totalHelp.text=response.body()?.data?.size.toString()
                    if(response.body()?.data!=null) {
                        binding.recyclerViewGeneral.adapter =
                            GeneralHelpAdapter(requireContext(), response.body()?.data!!)
                    }
                }
            }

        })
    }

}